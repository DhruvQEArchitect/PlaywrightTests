package com.ui.playwright.basic;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.nio.file.Paths;
import java.util.List;

public class FillForm {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext browserContext = browser.newContext();
            Page page = browserContext.newPage();
            page.navigate("https://www.orangehrm.com/en/30-day-free-trial");
            page.getByPlaceholder("Pick a username for your OrangeHRM trial.").fill("test");
            System.out.println(page.locator("span.description").textContent());
            PlaywrightAssertions.assertThat(page.locator("span.description")).isVisible();
            page.getByPlaceholder("Your Full Name*").fill("test");
            page.getByPlaceholder("Business Email*").fill("test");
            page.getByPlaceholder("Phone Number*").fill("test");
            List<String> getAllCountries = page.locator("select#Form_getForm_Country").allTextContents();
            getAllCountries.forEach(System.out::println);
            page.getByText("Get Your Free Trial").click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("form.png")));

            Browser browser2 = playwright.firefox().launch();
            BrowserContext browserContext2 = browser2.newContext();
            Page page2 = browserContext2.newPage();
            page2.navigate("https://www.bigbasket.com");
            List<String> aboutList = page2.locator("div.container div[class='w-1/3 pr-4'] a").allTextContents();
            aboutList.forEach(System.out::println);
            page2.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("bigbasket.png")));
        }
    }
}
