import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.nio.file.Paths;

public class DifferentSelectors {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext browserContext = browser.newContext();
            Page page = browserContext.newPage();
            page.navigate("https://www.orangehrm.com/en/pricing");
            page.locator("text='Submit'").click();
            PlaywrightAssertions.assertThat(page.locator("text='Submit'")).isVisible();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("locators.png")));

            String h2Text = page.locator("div.pricing-section h4:has-text('Get Pricing')").textContent();
            System.out.println(h2Text);
        }
    }
}
