package com.ui.playwright.basic;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class VideoRecordingTest {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
            launchOptions.setHeadless(true);
            launchOptions.setChannel("msedge");
            Browser browser = playwright.chromium().launch(launchOptions);
            BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("RecordedVideos/")));
            Page page = browserContext.newPage();
            page.navigate("https://www.orangehrm.com/en/pricing");
            page.fill("input[name='FullName']", "testuser");
            page.fill("input[name='Email']", "test@gmail.com");
            page.fill("input[name='CompanyName']", "company");
            page.fill("input[name='Contact']", "1234567899");
            page.selectOption("select[name='NoOfEmployees']", "0 - 10");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("video.png")));
            browserContext.close();
            page.close();
            playwright.close();
        }
    }
}
