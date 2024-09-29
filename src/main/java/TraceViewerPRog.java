import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class TraceViewerPRog {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            /**
             * Below code to turn on/off launch options
             */
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
            launchOptions.setHeadless(true);
            Browser browser = playwright.chromium().launch();
            BrowserContext context = browser.newContext();
            context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
            Page page = context.newPage();
            page.navigate("https://flipkart.com");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("web.png")));
            context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
        }
    }
}
