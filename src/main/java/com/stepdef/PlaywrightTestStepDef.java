package com.stepdef;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
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

    @When("^User clicks on login button on (.*)$")
    public void clickLogin(String app) {
        if (app.equalsIgnoreCase("flipkart")) {
            page.locator("(//a[@href='/account/login?ret=/'])[1]").click();
        }
        if (app.equalsIgnoreCase("amazon")) {
            page.getByText("Hello, sign in").click();
        }
        System.out.println("User clicked on login button successfully");
    }

    @Then("^User is logged in (.*)$")
    public void userLoggedIn(String app) {
        if (app.equalsIgnoreCase("flipkart")) {
            PlaywrightAssertions.assertThat(page.getByText("Request OTP")).isVisible();
        }
        if (app.equalsIgnoreCase("amazon")) {
            PlaywrightAssertions.assertThat(page.locator("#continue")).isVisible();
        }
        System.out.println("User is logged in successfully");
    }
}
