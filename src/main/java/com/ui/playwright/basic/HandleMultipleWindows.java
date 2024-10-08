package com.ui.playwright.basic;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;

public class HandleMultipleWindows {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext browserContext = browser.newContext();
            Page page = browserContext.newPage();
            page.navigate("https://www.orangehrm.com/en/pricing");
            Page newWindow = page.waitForPopup(() -> {
                page.click("div.social-icon > img[alt='linkedin logo']");
            });
            Thread.sleep(2000);
            System.out.println(newWindow.title());
            newWindow.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("childpop.png")));
            newWindow.close();
            System.out.println("Parent window: " + page.title());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("pop.png")));

            /**
             * open a new tab and perform action on it
             */
            Page blankWindow = page.waitForPopup(() -> {
                page.click("a[target='_blank']");
            });
            blankWindow.waitForLoadState();
            blankWindow.navigate("https://www.google.com");
            Thread.sleep(2000);
            System.out.println(blankWindow.title());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
