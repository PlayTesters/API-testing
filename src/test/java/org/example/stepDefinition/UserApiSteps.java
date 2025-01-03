package org.example.stepDefinition;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import io.cucumber.java.en.*;
import org.example.utils.ApiHelper;

public class UserApiSteps {
    private static final String BASE_URL = "http://localhost:7081";
    private APIRequestContext apiContext;
    private APIResponse response;

    public UserApiSteps() {
        Playwright playwright = Playwright.create();
        // Create API context with Basic Auth for user
        apiContext = ApiHelper.createApiContext(playwright, "user", "password");
    }

    @Given("User sends a POST request to {string} with body")
    public void userSendsAPOSTRequestToWithBody(String endpoint, String body) {
        response = apiContext.post(BASE_URL + endpoint, RequestOptions.create().setData(body));
        System.out.println("Response Status: " + response.status());
        System.out.println("Response Body: " + response.text());
    }

    @Given("User sends a GET request to {string}")
    public void userSendsAGETRequestTo(String endpoint) {
        response = apiContext.get(BASE_URL + endpoint);
        System.out.println("Response Status: " + response.status());
        System.out.println("Response Body: " + response.text());
    }


    @Given("User sends a PUT request to {string} with body")
    public void userSendsAPUTRequestToWithBody(String endpoint, String body) {
        response = apiContext.put(BASE_URL + endpoint, RequestOptions.create().setData(body));
        System.out.println("Response Status: " + response.status());
        System.out.println("Response Body: " + response.text());
    }

    @Given("User sends a DELETE request to {string}")
    public void userSendsADELETERequestTo(String endpoint) {
        response = apiContext.delete(BASE_URL + endpoint);
        System.out.println("Response Status: " + response.status());
        System.out.println("Response Body: " + response.text());
    }

    @Then("the user response status should be {int}")
    public void theUserResponseStatusShouldBe(int statusCode) {
        if (response.status() != statusCode) {
            throw new AssertionError("Expected status code " + statusCode + " but got " + response.status());

        }
    }

    @Then("the user response should contain {string}")
    public void theUserResponseShouldContain(String expectedContent) {
        if (!response.text().contains(expectedContent)) {
            throw new AssertionError("Expected user response to contain '" + expectedContent + "' but got: " + response.text());
        }
    }

}
