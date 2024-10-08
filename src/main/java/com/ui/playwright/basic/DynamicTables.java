package com.ui.playwright.basic;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class DynamicTables {
    static Page page;

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext browserContext = browser.newContext();
            page = browserContext.newPage();
            page.navigate("https://primeng.org/");
            System.out.println(page.title());
            if (page.locator("h5.m-0").textContent().equals("Customers")) {
                System.out.println("Verified table header successfully");
            }
            selectCustomer("Meaghan Garufi");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("dynamicTable.png")));
        }
    }

    static void selectCustomer(String customerName) {
        Locator getUpperLimitOfPage = page.locator("//span[@class='p-paginator-current']");
        String limit = getUpperLimitOfPage.textContent();
        String[] temp = limit.split(" ");
        System.out.println(temp[temp.length - 1]);
        int j = 0, i = Integer.parseInt(temp[temp.length - 1]);
        while (j < i) {
            if (page.locator("//*[text()=' " + customerName + " ']").isVisible()) {
                page.locator("//*[text()=' " + customerName + " ']/ancestor::tr/td[1]").click();
                break;
            } else if (page.locator("//button[@class='p-ripple p-element p-paginator-next p-paginator-element p-link']").isEnabled()
                    && page.locator("//button[@class='p-ripple p-element p-paginator-next p-paginator-element p-link']").isVisible()) {
                page.locator("//button[@class='p-ripple p-element p-paginator-next p-paginator-element p-link']").click();
                j++;
            }
        }
    }
}
