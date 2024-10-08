package com.ui.playwright.basic;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FilePayload;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadLogic {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext browserContext = browser.newContext();
            Page page = browserContext.newPage();
            page.navigate("https://demoqa.com/automation-practice-form");
            page.locator("input#uploadPicture").setInputFiles(Paths.get("test.png"));

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("fileUpload.png")));
            page.locator("input#uploadPicture").setInputFiles(new Path[0]);
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("fileUploadReset.png")));
            /**
             * we can upload multiple files as well using
             * below code
             */
//            page.locator("input#uploadPicture").setInputFiles(new Path[]{Paths.get("test.png"), Paths.get("test2.png")});

            //If the file does not exist in the dir and on the fly we want to create and upload a file
            page.locator("input#uploadPicture").setInputFiles(new FilePayload("fly.txt", "text/plain", "welcome to playright".getBytes(StandardCharsets.UTF_8)));
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("onFlyCreation.png")));
        }
    }
}
