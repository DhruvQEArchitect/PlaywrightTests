package com.ui.playwright.basic;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class DownloadLogicPlaywright {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
            launchOptions.setHeadless(false);
            Browser browser = playwright.chromium().launch(launchOptions);
            BrowserContext browserContext = browser.newContext();
            Page page = browserContext.newPage();
            page.navigate("https://demoqa.com/upload-download");
            page.locator("#uploadFile").setInputFiles(Paths.get("alerts.png"));
//            page.waitForDownload(() -> {
//
//
////                System.out.println(download.path());
//            });


        }
    }
}
