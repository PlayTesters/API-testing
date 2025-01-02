package org.example.stepDefinition;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import io.cucumber.java.en.*;
import org.example.utils.ApiHelper;

public class AdminApiSteps {
    private static final String BASE_URL = "http://localhost:7081";
    private APIRequestContext apiContext;
    private APIResponse response;

    public AdminApiSteps() {
        Playwright playwright = Playwright.create();
        // Create API context with Basic Auth
        apiContext = ApiHelper.createApiContext(playwright, "admin", "password");
    }

    @Given("Admin sends a POST request to {string} with body")
    public void adminSendsAPOSTRequestToWithBody(String endpoint, String body) {
        System.out.println("Sending POST request to: " + BASE_URL + endpoint);
        System.out.println("Request Body: " + body);

        response = apiContext.post(BASE_URL + endpoint,
                RequestOptions.create().setData(body));

        System.out.println("Response Status: " + response.status());
        System.out.println("Response Body: " + response.text());
    }


    @Given("Admin sends a GET request to {string}")
    public void adminSendsAGETRequestTo(String endpoint) {
        System.out.println("Sending GET request to: " + BASE_URL + endpoint);

        response = apiContext.get(BASE_URL + endpoint);

        System.out.println("Response Status: " + response.status());
        System.out.println("Response Body: " + response.text());
    }


    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        System.out.println("Validating response status...");
        if (response.status() != statusCode) {
            throw new AssertionError("Expected status code " + statusCode + " but got " + response.status());
        }
    }


    @Then("the response should contain {string}")
    public void theResponseShouldContain(String expectedContent) {
        String responseBody = response.text();
        System.out.println("Validating response content...");
        if (!responseBody.contains(expectedContent)) {
            throw new AssertionError("Expected response to contain '" + expectedContent + "' but got: " + responseBody);
        }
    }

    @Then("the response status should be 400 Bad Request")
    public void theResponseStatusShouldBe400BadRequest() {
        if (response.status() != 400) {
            throw new AssertionError("Expected status code 400 but got " + response.status());
        }
}

}
