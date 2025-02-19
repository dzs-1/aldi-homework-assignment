package com.aldi.example.ui.tests;

import com.aldi.example.ui.config.TestConfig;
import com.aldi.example.ui.pages.LoginPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        logger.info("Starting test setup...");

        try (Playwright playwright = Playwright.create()) {
            logger.info("Launching browser...");
            browser = playwright.chromium().launch();
            context = browser.newContext();
            page = context.newPage();
            loginPage = new LoginPage(page);

            String baseUrl = TestConfig.getBaseUrl();
            logger.info("Navigating to the login page: {}", baseUrl + "/login");
            page.navigate(baseUrl + "/login");
        } catch (Exception e) {
            logger.error("Failed to initialize Playwright: ", e);
            throw new RuntimeException("Failed to initialize Playwright", e);
        }
    }

    @Test
    public void testSuccessfulLogin() {
        logger.info("Starting test: Successful Login");

        loginPage.enterUsername(TestConfig.getUsername());
        logger.info("Entered username: {}", TestConfig.getUsername());

        loginPage.enterPassword(TestConfig.getPassword());
        logger.info("Entered password");

        loginPage.clickLogin();
        logger.info("Clicked on the login button");

        String currentUrl = page.url();
        logger.info("Current page URL: {}", currentUrl);
        assertTrue(currentUrl.contains("/dashboard"), "User was not redirected to the dashboard.");
        logger.info("Test Passed: Successful login verified.");
    }

    @Test
    public void testInvalidPassword() {
        logger.info("Starting test: Invalid Password");

        loginPage.enterUsername(TestConfig.getUsername());
        logger.info("Entered username: {}", TestConfig.getUsername());

        loginPage.enterPassword("wrongpassword");
        logger.info("Entered incorrect password");

        loginPage.clickLogin();
        logger.info("Clicked on the login button");

        String errorMessage = loginPage.getErrorMessage();
        logger.info("Error message displayed: {}", errorMessage);
        assertTrue(errorMessage.contains("Invalid credentials"), "Error message not displayed as expected.");
        logger.info("Test Passed: Invalid password scenario verified.");
    }

    @AfterEach
    public void tearDown() {
        logger.info("Cleaning up after the test...");

        if (page != null) {
            page.close();
            logger.info("Closed the page.");
        }
        if (context != null) {
            context.close();
            logger.info("Closed the browser context.");
        }
        if (browser != null) {
            browser.close();
            logger.info("Closed the browser.");
        }
    }
}