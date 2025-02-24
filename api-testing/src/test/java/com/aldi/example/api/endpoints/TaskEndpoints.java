package com.aldi.example.api.endpoints;

import com.aldi.example.api.config.TestConfig;

public class TaskEndpoints {

    private static final String BASE_URL = TestConfig.getBaseUrl();

    public static String createTask() {
        return BASE_URL + "/tasks";
    }

    public static String getTaskById(int taskId) {
        return BASE_URL + "/tasks/" + taskId;
    }

    public static String updateTaskById(int taskId) {
        return BASE_URL + "/tasks/" + taskId;
    }

    public static String deleteTaskById(int taskId) {
        return BASE_URL + "/tasks/" + taskId;
    }
}
