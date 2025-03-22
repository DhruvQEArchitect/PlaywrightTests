import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PlaywrightTest {

    Playwright playwright;
    APIRequestContext apiRequestContext;
    APIResponse apiResponse;
    APIRequest.NewContextOptions newContextOptions;

    @BeforeTest
    public void setContext() {
        playwright = Playwright.create();
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        newContextOptions = new APIRequest.NewContextOptions();
        newContextOptions.setExtraHTTPHeaders(headers);
        newContextOptions.setIgnoreHTTPSErrors(true);
        newContextOptions.setBaseURL("https://fakerapi.it/api/v2/addresses");
        apiRequestContext = playwright.request().newContext(newContextOptions);
    }

    @Test
    public void executeAddressApi() {
        apiResponse = apiRequestContext.get("");
        PlaywrightAssertions.assertThat(apiResponse).isOK();
    }

    @AfterTest
    public void tearContext() {
        playwright.close();
    }
}
