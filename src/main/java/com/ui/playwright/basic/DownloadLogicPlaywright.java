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

            //below snippet to upload a file
            page.locator("#uploadFile").setInputFiles(Paths.get("alerts.png"));

            //below snippet to download a file
            Download download = page.waitForDownload(() -> {
                page.locator("#downloadButton").click();
            });
            download.saveAs(Paths.get("./data/", download.suggestedFilename()));

        }
    }
}
