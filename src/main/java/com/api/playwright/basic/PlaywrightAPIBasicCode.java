package com.api.playwright.basic;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

import java.util.HashMap;
import java.util.Map;

public class PlaywrightAPIBasicCode {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            APIRequestContext request = playwright.request().newContext();

            APIResponse response = request.get("https://jsonplaceholder.typicode.com/posts/1");

            System.out.println("Status Code: " + response.status());
            System.out.println("Response Body: " + response.text());
        }
    }

}
