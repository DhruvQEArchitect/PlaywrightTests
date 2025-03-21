package com.api.playwright.basic;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.RequestOptions;

import java.util.HashMap;
import java.util.Map;

public class PlaywrightAPIBasicCode {
    static Playwright playwright;
    static APIRequestContext request;
    static APIResponse response;

    public static void main(String[] args) {
        getUser("https://reqres.in/api/users");
        createUser("https://reqres.in/api/users");
    }

    public static void buildContext(String url) {
        playwright = Playwright.create();
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        APIRequest.NewContextOptions contextOptions = new APIRequest.NewContextOptions();
        contextOptions.setIgnoreHTTPSErrors(true);
        contextOptions.setExtraHTTPHeaders(headers);
        contextOptions.setBaseURL(url);
        request = playwright.request().newContext(contextOptions);
    }

    public static void getUser(String url) {
        buildContext(url);
        response = request.get("");
        PlaywrightAssertions.assertThat(response).isOK();
        System.out.println("Status Code: " + response.status());
        System.out.println("Response Body: " + response.text());
    }

    public static void createUser(String url) {
        response = request.post(url, RequestOptions.create().setData("{\n" +
                "    \"name\": \"DKTest\",\n" +
                "    \"job\": \"Engineer\"\n" +
                "}"));
        PlaywrightAssertions.assertThat(response).isOK();
        System.out.println("User created successfully");
        System.out.println("Status Code: " + response.status());
        System.out.println("Response Body: " + response.text());
    }

}
