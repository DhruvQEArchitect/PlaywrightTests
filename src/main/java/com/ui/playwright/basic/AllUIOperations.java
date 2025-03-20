package com.ui.playwright.basic;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.MouseButton;

import java.awt.*;
import java.nio.file.Paths;
import java.util.List;

public class AllUIOperations {
    static Playwright playwright = Playwright.create();
    static Page page, newPage, newWin;
    static BrowserContext browserContext;
    static Browser browser;
    static Dimension dimension;
    static int height, width;

    public static void main(String[] args) {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        height = (int) dimension.getHeight();
        width = (int) dimension.getWidth();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false);
        browser = playwright.chromium().launch(launchOptions);
        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.setViewportSize(width, height);
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
        page.locator("//button[@class='close']/span[1]").click();
        page.locator("//span[@class='select-wrap -pageSizeOptions']/select").selectOption("20 rows");
        captureScreenshot(page, "WebTable");

        page.getByText("Buttons").click();
        page.getByText("Double Click Me").dblclick();
        PlaywrightAssertions.assertThat(page.getByText("You have done a double click")).isVisible();
        page.getByText("Right Click Me").click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
        PlaywrightAssertions.assertThat(page.getByText("You have done a right click")).isVisible();
        page.locator("//*[text()='Click Me']").click();
        PlaywrightAssertions.assertThat(page.getByText("You have done a dynamic click")).isVisible();
        captureScreenshot(page, "Buttons");


        page.locator("//*[text()=\"Links\"]").click();
        //find all links on the page
        List<String> linkText = page.locator("//div[@id='linkWrapper']//following::a").allTextContents();
        System.out.println(linkText);
        captureScreenshot(page, "LinkText");

//        page.getByText("Broken Links - Images").click();
//        page.getByText("Click Here for Broken Link").click();
//        PlaywrightAssertions.assertThat(page.getByText("This page returned a 500 status code.")).isVisible();
//        captureScreenshot(page, "BrokenLinkTest");
//        page.goBack();

        page.getByText("Upload and Download").click();
        page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Upload and Download")).isVisible();
        page.locator("#uploadFile").setInputFiles(Paths.get("./data/sampleFile.jpeg"));
        page.locator("#downloadButton").click();
        captureScreenshot(page, "Upload");
        page.onDownload(download -> {
            download.saveAs(Paths.get("./data/", download.suggestedFilename()));
        });
        captureScreenshot(page, "Download");

        page.getByText("Alerts, Frame & Windows").click();
        page.getByText("Browser Windows").click();

        newPage = browserContext.waitForPage(() -> {
            page.getByText("New Tab").click();
        });
        PlaywrightAssertions.assertThat(newPage.getByText("This is a sample page")).isVisible();
        newPage.navigate("https://www.google.com");
        captureScreenshot(newPage, "NewTab");
        newPage.close();

        newWin = page.waitForPopup(() -> {
            page.locator("//*[text()='New Window']").click();
        });
        System.out.println(newWin.title());
        newWin.navigate("https://www.google.com");
        newWin.setViewportSize(width, height);
        captureScreenshot(newWin, "NewWin");
        newWin.close();

        page.locator("//*[text()='Alerts']").click();

        page.onDialog(dialog -> {
            System.out.println(dialog.message());
            dialog.accept();
        });
        page.locator("//button[@id='alertButton']").click();
        captureScreenshot(page, "Dialog");

        page.locator("//*[text()='Frames']").click();
        System.out.println(page.frameLocator("#frame1").locator("#sampleHeading").textContent());
        captureScreenshot(page, "Frames");

        page.getByText("Modal Dialogs").click();
        page.locator("#showSmallModal").click();
        captureScreenshot(page, "ModalDialogs");
        page.locator("#closeSmallModal").click();

        page.getByText("Widgets").click();
        page.getByText("Auto Complete").click();
        page.locator("#autoCompleteMultipleInput").fill("re");
        page.keyboard().press("Enter");
        captureScreenshot(page, "AutoComplete");

        page.getByText("Date Picker").click();
        page.locator("#datePickerMonthYearInput").clear();
        page.locator("#datePickerMonthYearInput").fill("03/10/2026");
        page.locator("#dateAndTimePickerInput").clear();
        page.locator("#dateAndTimePickerInput").fill("March 21, 2025 8:30 PM");
        captureScreenshot(page, "DatePicker");

        /**
         * move slider until it reaches a specific value
         */
        page.getByText("Slider").click();
        int i = 0;
        page.locator("//*[@class='range-slider range-slider--primary']").focus();
        while (i < 2000) {
            try {
                page.keyboard().press("ArrowRight");
                if (page.locator("//*[@class='range-slider range-slider--primary' and @value = \"86\"]").isVisible()) {
                    break;
                }
            } catch (Exception ex) {
                i++;
            }

        }
        captureScreenshot(page, "Slider");

        /**
         * below snippet to handle progress bar
         */
        page.getByText("Progress Bar").click();
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Progress Bar"))).isVisible();
        page.locator("#startStopButton").click();
        while (i < 2000) {
            try {
                if (page.locator("//div[@role='progressbar' and @aria-valuenow=\"26\"]").isVisible()) {
                    page.locator("#startStopButton").click();
                    break;
                }
            } catch (Exception ex) {
                i++;
            }
        }
        captureScreenshot(page, "ProgressBar");

        /**
         * below snippet to handle tooltip
         */
        page.getByText("Tool Tips").click();
        page.getByText("Hover me to see").hover();
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.TOOLTIP, new Page.GetByRoleOptions().setName("You hovered over the Button"))).isVisible();
        captureScreenshot(page, "ToolTip");

        page.locator("//*[text()='Menu']").click();
        page.getByText("Main Item 2").hover();

        int count = page.locator("//*[text()='Main Item 2']/..//ul").count();

        for (int j = 1; j <= count; j++) {
            int liCount = page.locator("(//*[text()='Main Item 2']/..//ul)[" + j + "]/li").count();
            for (int k = 1; k <= liCount; k++) {
                System.out.println(page.locator("(//*[text()='Main Item 2']/..//ul)[" + j + "]/li[" + k + "]").allTextContents());
            }
        }
        captureScreenshot(page, "Menu");

        page.close();
        browserContext.close();
        browser.close();
        playwright.close();
    }

    public static void captureScreenshot(Page page, String captureName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./Screenshots/" + captureName + ".png")));
    }
}
