import com.microsoft.playwright.*;

import java.nio.file.Paths;
import java.util.List;

public class HandleElements {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://www.orangehrm.com/en/pricing");

            //handling single elements
            Locator fullName = page.locator("//input[@name='FullName']");
            fullName.fill("testuser");

            //getting text from an element
            Locator pricingText = page.locator("//div[@class='pricing-section']//h4");
            System.out.println(pricingText.textContent());

            Locator noOfEmployees = page.locator("//select[@name='NoOfEmployees']");
            noOfEmployees.selectOption("0 - 10");

            for (int i = 0; i < noOfEmployees.count(); i++) {
                System.out.println(noOfEmployees.nth(i).textContent());
            }

            //below allTextContents method return a list of String
            List<String> anotherWay = noOfEmployees.allTextContents();
            for (String str : anotherWay)
                System.out.println(str);

            anotherWay.forEach(System.out::println);

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("emp.png")));
        }
    }
}
