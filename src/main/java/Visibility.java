import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;
import java.util.List;

public class Visibility {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext browserContext = browser.newContext();
            Page page = browserContext.newPage();
            page.navigate("https://www.flipkart.com");
            List<String> getAllLinks = page.locator("a:visible").allTextContents();
            for (String str : getAllLinks) {
                System.out.println(str);
            }
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("allLinks.png")));
        }
    }
}
