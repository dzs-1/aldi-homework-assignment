package com.aldi.example.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aldi.example.pages.TaskPage;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.logging.Logger;

public class DeleteTaskTest {
    private static final Logger logger = Logger.getLogger(DeleteTaskTest.class.getName());
    private WebDriver driver;
    private TaskPage taskPage;

    @BeforeEach
    void setUp() {
        logger.info("Setting up the environment...");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-notifications",
                "--start-maximized",
                "--disable-infobars",
                "--disable-gpu",
                "--disable-extensions",
                "--no-sandbox",
                "--disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.get("https://aldi-example.com");
        taskPage = new TaskPage(driver);
        logger.info("Navigated to the homepage.");
    }

    @Test
    void shouldDeleteTask() {
        String taskId = "ALDI-123";

        taskPage.deleteTask(taskId);
        logger.info("Deleting task completed for task ID: " + taskId);

        boolean taskDeleted = taskPage.isTaskDeleted(taskId);
        Assertions.assertTrue(taskDeleted, "Task should be deleted");
        logger.info("Task deletion verified for task ID: " + taskId);

        String successMessage = taskPage.getSuccessMessage();
        Assertions.assertEquals("Task deleted successfully!", successMessage, "Success message should be correct");
        logger.info("Success message verified: " + successMessage);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Closing browser.");
        }
    }
}