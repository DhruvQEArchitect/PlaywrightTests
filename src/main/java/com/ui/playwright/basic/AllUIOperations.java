package com.ui.playwright.basic;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class AllUIOperations {
    static Playwright playwright = Playwright.create();
    static Page page;
    static BrowserContext browserContext;
    static Browser browser;

    public static void main(String[] args) {

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false);
        browser = playwright.chromium().launch(launchOptions);
        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate("https://demoqa.com/elements");
        PlaywrightAssertions.assertThat(page.getByText("Please select an item from left to start practice.")).isVisible();
        System.out.println("Page title: " + page.title());
        captureScreenshot(page, "LandingPage");

        page.getByText("Text Box").click();
        page.getByPlaceholder("Full Name").fill("full name");
        page.locator("#userEmail").fill("test@test.com");
        page.locator("#currentAddress").fill("address");
        page.locator("#permanentAddress").fill("permanent address");
        page.getByText("Submit").click();
        captureScreenshot(page, "TextBox");

        page.getByText("Check Box").click();
        page.getByRole(AriaRole.CHECKBOX);
        captureScreenshot(page, "CheckBox");

        page.getByText("Radio Button").click();
        page.locator("//*[text()=\"Yes\"]").click();
        PlaywrightAssertions.assertThat(page.getByText("You have selected Yes")).isVisible();
        captureScreenshot(page, "Radio");

        page.getByText("Web Tables").click();
        page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Web Tables")).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
        PlaywrightAssertions.assertThat(page.locator("#submit")).isVisible();
        captureScreenshot(page, "WebTable");
        page.close();
        browserContext.close();
        browser.close();
        playwright.close();
    }

    public static void captureScreenshot(Page page, String captureName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./Screenshots/" + captureName + ".png")));
    }
}
