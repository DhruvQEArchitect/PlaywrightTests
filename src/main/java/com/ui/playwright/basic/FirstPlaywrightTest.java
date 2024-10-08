package com.ui.playwright.basic;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.awt.*;
import java.nio.file.Paths;

public class FirstPlaywrightTest {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            /**
             * By default, the browser launches in headless mode in playwright
             */
            Browser browser = playwright.firefox().launch();
            //to get screen resolution
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int width = dimension.width;
            int height = dimension.height;
            /**
             * To run browser in headed mode
             */
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.setViewportSize(width, height);
//            page.navigate("https://demo.guru99.com/test/newtours/register.php");
            /**
             * To get the page title
             */
//            System.out.println(page.title());

            /**
             * To locate elements on the webpage
             */
//            Locator firstName = page.locator("//input[@name='firstName']");
//            Locator countryOptions = page.locator("//select[@name='country']");
            /**
             * To send data in input fields or to type data in search boxes or input or textboxes use fill method
             */
//            firstName.fill("Test user");
//            if (countryOptions.isEnabled()) {
//                countryOptions.selectOption("ALGERIA");
//            }

            /**
             * drag and drop
             */
            page.navigate("https://demoqa.com/droppable");
            page.locator("//div[@id='draggable']").dragTo(page.locator("//div[@class='simple-drop-container']//div[@id='droppable']"));
            //capture screenshots in playwright
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test.png")));
            PlaywrightAssertions.assertThat(page).hasTitle(page.title());

            //keyboard actions in playwright
            page.keyboard().press("Control+A");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("newtab.png")));
        }
    }
}
