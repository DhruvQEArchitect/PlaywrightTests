import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class HandleElements {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://www.orangehrm.com/en/pricing");

            Locator fullName = page.locator("//input[@name='FullName']");
            fullName.fill("testuser");

            Locator noOfEmployees = page.locator("//select[@name='NoOfEmployees']");
            noOfEmployees.selectOption("0 - 10");

            for (int i = 0; i < noOfEmployees.count(); i++) {
                System.out.println(noOfEmployees.nth(i).textContent());
            }
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("emp.png")));
        }
    }
}
