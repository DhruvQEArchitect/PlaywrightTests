package com.api.playwright.basic;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.RequestOptions;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PlaywrightAPIBasicCode {
    static Playwright playwright;
    static APIRequestContext request;
    static APIResponse response;
    static APIRequest.NewContextOptions contextOptions;

    public static void main(String[] args) {
        getUser("https://reqres.in/api/users");
        createUser("https://reqres.in/api/users");
        updateUser("https://reqres.in/api/users/2");
        updateJob("https://reqres.in/api/users/2");
        authenticateUser("https://dummyjson.com/auth/me");
        playwright.close();

    }

    public static void buildContext(String url) {
        playwright = Playwright.create();
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        contextOptions = new APIRequest.NewContextOptions();
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
        response = request.post(url, RequestOptions.create().setData("{\n" + "    \"name\": \"DKTest\",\n" + "    \"job\": \"Engineer\"\n" + "}"));
        PlaywrightAssertions.assertThat(response).isOK();
        System.out.println("User created successfully");
        System.out.println("Status Code: " + response.status());
        System.out.println("Response Body: " + response.text());
    }

    public static void updateUser(String url) {
        response = request.put(url, RequestOptions.create().setData("{\n" + "    \"name\": \"morpheus\",\n" + "    \"job\": \"zion\"\n" + "}"));
        PlaywrightAssertions.assertThat(response).isOK();
        System.out.println("User updated successfully");
        System.out.println("Status Code: " + response.status());
        System.out.println("Response Body: " + response.text());
    }

    public static void updateJob(String url) {
        response = request.patch(url, RequestOptions.create().setData("{\n" + "    \"job\": \"test engineer\"\n" + "}"));
        PlaywrightAssertions.assertThat(response).isOK();
        System.out.println("Job updated successfully");
        System.out.println("Status Code: " + response.status());
        System.out.println("Response Body: " + response.text());
    }

    public static String generateToken(String url) {
        buildContext(url);
        response = request.post(url, RequestOptions.create().setData("{\n" + "    \"username\": \"emilys\",\n" + "    \"password\": \"emilyspass\"\n" + "}"));
        System.out.println("Token generated successfully");
        System.out.println("Status Code: " + response.status());
        System.out.println("Response Body: " + response.text());
        JSONObject jsonObject = new JSONObject(response.text());
        return jsonObject.getString("accessToken");
    }

    public static void buildAuthContext(String url) {
        playwright = Playwright.create();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", generateToken("https://dummyjson.com/auth/login"));
        contextOptions = new APIRequest.NewContextOptions();
        contextOptions.setIgnoreHTTPSErrors(true);
        contextOptions.setBaseURL(url);
        contextOptions.setExtraHTTPHeaders(headers);
        request = playwright.request().newContext(contextOptions);
    }

    public static void authenticateUser(String url) {
        buildAuthContext(url);
        response = request.get("");
        System.out.println("Token generated successfully");
        System.out.println("Status Code: " + response.status());
        System.out.println("Response Body: " + response.text());
    }

}
