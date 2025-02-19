package com.aldi.example.ui.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    private final static String usernameField = "[data-testid='username-input']";
    private final static String passwordField = "[data-testid='password-input']";
    private final static String loginButton = "[data-testid='login-button']";
    private final static String errorMessage = "[data-testid='error-message']";

    public void enterUsername(String username) {
        page.fill(usernameField, username);
    }

    public void enterPassword(String password) {
        page.fill(passwordField, password);
    }

    public void clickLogin() {
        page.click(loginButton);
    }

    public String getErrorMessage() {
        return page.innerText(errorMessage);
    }
}