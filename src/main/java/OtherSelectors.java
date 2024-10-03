import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.nio.file.Paths;
import java.util.List;

public class OtherSelectors {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
            launchOptions.setHeadless(false);
            Browser browser = playwright.chromium().launch(launchOptions);
            BrowserContext browserContext = browser.newContext();
            Page page = browserContext.newPage();
            page.setViewportSize(1920, 1080);
            page.navigate("https://www.flipkart.com");
            List<String> list = page.locator(":has-text('Destination')").allTextContents();

            Locator searchInput = page.locator("input.Pke_EE:left-of(:text('Login'))");
            searchInput.fill("laptop");
            Thread.sleep(5000);
            Locator results = page.locator("//ul[@class='_1sFryS _2x2Mmc _3ofZy1']//child::span//parent::div");
            List<String> resultList = results.allTextContents();
            resultList.forEach(System.out::println);
            Locator cart = page.locator("a[title=\"Cart\"]>img:right-of(:text(\"Login\"))");
            cart.click();
//            Locator loginButton = page.locator("div>a[href='/account/login?ret=/']>span");
//            loginButton.click();
//            Locator requestOTPButton = page.getByText("Request OTP");
//            requestOTPButton.click();
//
//            PlaywrightAssertions.assertThat(page.locator("text='Please enter valid Email ID/Mobile number"));
            Locator missingCart = page.locator("div.s2gOFd:above(:text(\"Login\"))");
            System.out.println(missingCart.textContent());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("login.png")));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
