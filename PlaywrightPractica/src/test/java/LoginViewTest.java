import com.microsoft.playwright.*;
import org.checkerframework.checker.guieffect.qual.SafeType;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginViewTest {

    private static Playwright playwright;
    private static Browser browser;
    protected Page page;
    private BrowserContext browserContext;

    @BeforeAll
    static void beforeAll() {
        playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.headless = false;
        browser = browserType.launch(launchOptions);
    }

    @AfterAll
    static void afterAll() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void beforeEach(){
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterEach
    void afterEach(){
        page.close();
        browserContext.close();
    }

    @Test
    public void FieldsTest() {
        page.navigate("http://localhost:8080/login");

        Locator loginForm = page.locator("#login-form");
        assertThat(loginForm).isVisible();

        // Verificar los campos de entrada para el nombre de usuario y la contraseña
        Locator usernameField = page.locator("#input-vaadin-text-field-6");
        Locator passwordField = page.locator("#input-vaadin-password-field-7");
        assertThat(usernameField).isVisible();
        assertThat(passwordField).isVisible();

        // Llenar los campos de entrada con datos
        usernameField.fill("admin");  // Reemplaza con el nombre de usuario deseado
        passwordField.fill("admin");  // Reemplaza con la contraseña deseada

        page.locator("vaadin-button").getByText("Log in").click();

        // Espera explícita para el mensaje de éxito
        Locator successMessage = page.locator("#success-message");
        assertThat(successMessage).isVisible();
    }


}
