package com.aldi.example.api.tests;

import com.aldi.example.api.data.TestData;
import com.aldi.example.api.endpoints.TaskEndpoints;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class TaskApiTests {

    @Test
    public void testCreateTask_ValidRequest() {
        String payload = String.format("{\"name\": \"%s\", \"description\": \"%s\"}",
                TestData.TASK_NAME, TestData.TASK_DESCRIPTION);

        RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(TaskEndpoints.createTask())
                .then()
                .statusCode(201)
                .body("message", equalTo("Task Created"))
                .extract()
                .response();
    }

    @Test
    public void testCreateTask_MissingTaskName() {
        String payload = String.format("{\"description\": \"%s\"}", TestData.TASK_DESCRIPTION);

        RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(TaskEndpoints.createTask())
                .then()
                .statusCode(400)
                .body("message", equalTo("Task name is required"))
                .extract()
                .response();
    }

    @Test
    public void testCreateTask_InvalidTaskName() {
        String payload = String.format("{\"name\": 12345, \"description\": \"%s\"}", TestData.TASK_DESCRIPTION);

        RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(TaskEndpoints.createTask())
                .then()
                .statusCode(400)
                .body("message", equalTo("Invalid task name format"))
                .extract()
                .response();
    }

    @Test
    public void testCreateTask_DuplicateTaskName() {
        String payload = String.format("{\"name\": \"%s\", \"description\": \"%s\"}",
                TestData.TASK_NAME, TestData.TASK_DESCRIPTION);

        RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(TaskEndpoints.createTask())
                .then()
                .statusCode(201);

        RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(TaskEndpoints.createTask())
                .then()
                .statusCode(409)
                .body("message", equalTo("Task with the same name already exists"))
                .extract()
                .response();
    }

    @Test
    public void testGetTaskById_ValidId() {
        int taskId = 1;

        RestAssured.given()
                .when()
                .get(TaskEndpoints.getTaskById(taskId))
                .then()
                .statusCode(200)
                .body("id", equalTo(taskId))
                .body("name", equalTo("Test Task"))
                .extract()
                .response();
    }

    @Test
    public void testGetTaskById_InvalidId() {
        int invalidTaskId = 9999;

        RestAssured.given()
                .when()
                .get(TaskEndpoints.getTaskById(invalidTaskId))
                .then()
                .statusCode(404)
                .body("message", equalTo("Task not found"))
                .extract()
                .response();
    }

    @Test
    public void testGetTaskById_MissingId() {
        RestAssured.given()
                .when()
                .get(TaskEndpoints.getTaskById(-1))
                .then()
                .statusCode(400)
                .body("message", equalTo("Invalid task ID"))
                .extract()
                .response();
    }

    @Test
    public void testGetTaskById_TaskWithNoDescription() {
        int taskId = 2;

        RestAssured.given()
                .when()
                .get(TaskEndpoints.getTaskById(taskId))
                .then()
                .statusCode(200)
                .body("id", equalTo(taskId))
                .body("description", equalTo(""))
                .extract()
                .response();
    }

    @Test
    public void testUpdateTaskById_ValidUpdate() {
        int taskId = 1;
        String updatedPayload = String.format("{\"name\": \"%s\", \"description\": \"%s\"}",
                TestData.UPDATED_TASK_NAME, TestData.UPDATED_TASK_DESCRIPTION);

        RestAssured.given()
                .contentType("application/json")
                .body(updatedPayload)
                .when()
                .put(TaskEndpoints.updateTaskById(taskId))
                .then()
                .statusCode(200)
                .body("name", equalTo(TestData.UPDATED_TASK_NAME))
                .body("description", equalTo(TestData.UPDATED_TASK_DESCRIPTION))
                .extract()
                .response();
    }

    @Test
    public void testUpdateTaskById_InvalidId() {
        int invalidTaskId = 9999;
        String updatedPayload = String.format("{\"name\": \"%s\", \"description\": \"%s\"}",
                TestData.UPDATED_TASK_NAME, TestData.UPDATED_TASK_DESCRIPTION);

        RestAssured.given()
                .contentType("application/json")
                .body(updatedPayload)
                .when()
                .put(TaskEndpoints.updateTaskById(invalidTaskId))
                .then()
                .statusCode(404)
                .body("message", equalTo("Task not found"))
                .extract()
                .response();
    }

    @Test
    public void testUpdateTaskById_MissingFields() {
        int taskId = 1;
        String updatedPayload = "{\"description\": \"Updated Description only\"}"; // Missing task name

        RestAssured.given()
                .contentType("application/json")
                .body(updatedPayload)
                .when()
                .put(TaskEndpoints.updateTaskById(taskId))
                .then()
                .statusCode(400)
                .body("message", equalTo("Task name is required"))
                .extract()
                .response();
    }

    @Test
    public void testUpdateTaskById_InvalidFormat() {
        int taskId = 1;
        String updatedPayload = "{\"name\": 12345, \"description\": \"Updated Description\"}"; // Invalid task name type

        RestAssured.given()
                .contentType("application/json")
                .body(updatedPayload)
                .when()
                .put(TaskEndpoints.updateTaskById(taskId))
                .then()
                .statusCode(400)
                .body("message", equalTo("Invalid task name format"))
                .extract()
                .response();
    }

    @Test
    public void testDeleteTaskById_ValidId() {
        int taskId = 1;

        RestAssured.given()
                .when()
                .delete(TaskEndpoints.deleteTaskById(taskId))
                .then()
                .statusCode(204)
                .body("message", equalTo("Task Deleted"))
                .extract()
                .response();
    }

    @Test
    public void testDeleteTaskById_InvalidId() {
        int invalidTaskId = 9999;

        RestAssured.given()
                .when()
                .delete(TaskEndpoints.deleteTaskById(invalidTaskId))
                .then()
                .statusCode(404)
                .body("message", equalTo("Task not found"))
                .extract()
                .response();
    }

    @Test
    public void testDeleteTaskById_AlreadyDeleted() {
        int taskId = 1;

        RestAssured.given()
                .when()
                .delete(TaskEndpoints.deleteTaskById(taskId))
                .then()
                .statusCode(204);
    }

    @Test
    public void testDeleteTaskById_MissingId() {
        RestAssured.given()
                .when()
                .delete(TaskEndpoints.deleteTaskById(-1)) // Missing valid task ID
                .then()
                .statusCode(400)
                .body("message", equalTo("Invalid task ID"))
                .extract()
                .response();
    }
}