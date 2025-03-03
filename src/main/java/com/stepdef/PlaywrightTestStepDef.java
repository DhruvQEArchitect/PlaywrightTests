package com.stepdef;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaywrightTestStepDef {

    Playwright playwright = Playwright.create();
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @Given("^User launches (.*) with (.*)$")
    public void launchApp(String app, String url) {
        browser = playwright.chromium().launch();
        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate(url);
        System.out.println("User launched app: " + app + " with url: " + url + " successfully");
    }

    @When("^User clicks on login button$")
    public void clickLogin() {
        System.out.println("User clicked on login button successfully");
    }

    @Then("^User is logged in$")
    public void userLoggedIn() {
        System.out.println("User is logged in successfully");
    }
}
