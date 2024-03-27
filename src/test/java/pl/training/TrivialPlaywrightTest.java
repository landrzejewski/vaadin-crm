package pl.training;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Tag("playwright")
public class TrivialPlaywrightTest {

    @LocalServerPort
    private int port;

    static Playwright playwright = Playwright.create();

    @Test
    public void testClicking() {
        var browser = playwright.chromium().launch(
                new BrowserType
                        .LaunchOptions()
                        .setHeadless(false)
        );

        var page = browser.newPage();
        page.navigate("http://localhost:" + port + "/hello");

        login(page, "admin", "admin");

        assertThat(page.getByText("Click Me")).isVisible();
        page.locator("//vaadin-button[contains(text(),'Click me')]").click();
        assertThat(page.locator("#msg")).containsText("Clicked!");
    }

    private void login(Page page, String username, String password) {
        page.locator("input[name=\"username\"]")
                .fill(username);
        page.locator("input[name=\"password\"]")
                .fill(password);
        page.locator("//vaadin-button[contains(text(),'Log in')]").click();
    }

}
