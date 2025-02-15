package com.aldi.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class TaskPage {
    private final WebDriverWait wait;

    private static final String DELETE_BUTTON = "[data-testid='delete-task-button']";
    private static final String CONFIRM_DELETE = "[data-testid='confirm-delete-button']";
    private static final String TASK_ITEM = "[data-testid='task-item-%s']";
    private static final String SUCCESS_MESSAGE = "[data-testid='success-message']";

    public TaskPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void deleteTask(String taskId) {
        WebElement task = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(String.format(TASK_ITEM, taskId))));
        task.findElement(By.cssSelector(DELETE_BUTTON)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(CONFIRM_DELETE))).click();
    }

    public boolean isTaskDeleted(String taskId) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(String.format(TASK_ITEM, taskId))));
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SUCCESS_MESSAGE))).getText();
    }
}
