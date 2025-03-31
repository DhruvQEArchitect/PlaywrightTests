package stepdef;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class APIStepDef {

    Playwright playwright;
    APIRequest.NewContextOptions contextOptions;
    APIRequestContext apiRequestContext;
    APIResponse apiResponse;

    @Given("^User executes address API (.*)$")
    public void executeApi(String endpoint) {
        playwright = Playwright.create();
        contextOptions = new APIRequest.NewContextOptions();
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        contextOptions.setIgnoreHTTPSErrors(true);
        contextOptions.setExtraHTTPHeaders(headers);
        contextOptions.setBaseURL(endpoint);
        apiRequestContext = playwright.request().newContext(contextOptions);
        apiResponse = apiRequestContext.get("");
    }

    @When("^User fetches data$")
    public void fetchData() {
        System.out.println(apiResponse.text());
    }

    @Then("Address data should not be empty$")
    public void verifyData() {
        JSONArray jsonArray = new JSONArray(apiResponse.text());
        for (int i = 0; i < jsonArray.length(); i++) {
            Assert.assertNotNull(jsonArray.getJSONObject(i).get("slug"));
            Assert.assertNotNull(jsonArray.getJSONObject(i).get("name"));
            Assert.assertNotNull(jsonArray.getJSONObject(i).get("url"));
        }
        System.out.println("verified successfully");

    }
}
