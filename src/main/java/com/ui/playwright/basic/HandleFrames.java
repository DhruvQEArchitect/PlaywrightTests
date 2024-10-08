package com.ui.playwright.basic;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class HandleFrames {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
            launchOptions.setHeadless(true);
            Browser browser = playwright.chromium().launch(launchOptions);
            Page page = browser.newPage();
            page.navigate("https://demo.guru99.com/test/guru99home/");
//            page.frameLocator("//*[@name='a077aa5e']").locator("a").click();
            page.frame("a077aa5e").locator("a").click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("frames.png")));
        }
    }
}