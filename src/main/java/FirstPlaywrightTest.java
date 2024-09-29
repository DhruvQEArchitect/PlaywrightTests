import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.nio.file.Paths;

public class FirstPlaywrightTest {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            /**
             * By default, the browser launches in headless mode in playwright
             */
            Browser browser = playwright.firefox().launch();
            Page page = browser.newPage();
            page.setViewportSize(1920, 1080);
            page.navigate("https://demo.guru99.com/test/newtours/register.php");
            /**
             * To get the page title
             */
            System.out.println(page.title());

            /**
             * To locate elements on the webpage
             */
            Locator firstName = page.locator("//input[@name='firstName']");

            /**
             * To send data in input fields or to type data in search boxes or input or textboxes use fill method
             */
            firstName.fill("Test user");
            if (page.locator("//select[@name='country']").isEnabled()) {
                page.locator("//select[@name='country']").selectOption("ALGERIA");
            }
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test.png")));
            PlaywrightAssertions.assertThat(page).hasTitle(page.title());
        }
    }
}
