package com.ui.playwright.basic;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public class PlaywrightAssert {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        BrowserContext browserContext = browser.newContext();
        Page page = browserContext.newPage();
        page.navigate("https://demoqa.com/text-box");
        Locator fullName = page.getByPlaceholder("Full Name");
        Locator submitButton = page.locator("#submit");
        Locator currentAddress = page.getByPlaceholder("Current Address");

        /**
         * below are some assertions which can be applied to locators
         */
        PlaywrightAssertions.assertThat(fullName).hasId("userName");

        PlaywrightAssertions.assertThat(submitButton).hasText("Submit");

        PlaywrightAssertions.assertThat(submitButton).containsText("Submit");
        
        PlaywrightAssertions.assertThat(submitButton).hasRole(AriaRole.BUTTON);

        PlaywrightAssertions.assertThat(currentAddress).hasAttribute("rows", "5");

        browserContext.close();
        browser.close();
        playwright.close();
    }

}
