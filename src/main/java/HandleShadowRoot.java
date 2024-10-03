import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;

public class HandleShadowRoot {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext browserContext = browser.newContext();
            Page page = browserContext.newPage();
            page.navigate("https://books-pwakit.appspot.com/");
            page.locator("book-app[apptitle='BOOKS'] #input").fill("testing");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("shadowroot.png")));




        }
    }
}
