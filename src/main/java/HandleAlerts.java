import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.nio.file.Paths;

public class HandleAlerts {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext browserContext = browser.newContext();
            Page page = browserContext.newPage();
            page.onDialog(dialog -> {
                System.out.println(dialog.message());
                dialog.accept();
            });
            page.navigate("https://demo.guru99.com/test/delete_customer.php");
            page.click("//input[@type='submit']");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("alerts.png")));

            Browser browser1 = playwright.firefox().launch();
            BrowserContext browserContext1 = browser1.newContext();
            Page page1 = browserContext1.newPage();
            page1.onDialog(dialog -> {
                System.out.println(dialog.message());
                dialog.accept("hello");
            });
            page1.navigate("https://demoqa.com/alerts");

            page1.click("button#promtButton");
            page1.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("alertVerification.png")));
            PlaywrightAssertions.assertThat(page1.locator("//*[text()='hello']")).isVisible();

        }
    }
}
