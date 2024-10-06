import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;

public class AutoLoginMechanism {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            //when using the login again in the script use below
            BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("login.json")));
            Page page = browserContext.newPage();
            page.navigate("https://flipkart.com");
            //while first time logging in use below to store cookies
            browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("login.json")));
        }
    }
}
