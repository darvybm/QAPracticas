import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ContactFormTest {

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
    public void invalidEmailTest() {
        page.navigate("http://localhost:8080/contact");

        // Llenar los campos del formulario con un correo electrónico no válido
        Locator nameField = page.locator("#input-vaadin-text-field-9");
        Locator emailField = page.locator("#input-vaadin-email-field-10");
        Locator messageField = page.locator("#textarea-vaadin-text-area-11");

        nameField.fill("John Doe");
        emailField.fill("not-an-email");
        messageField.fill("Este es un mensaje de prueba.");

        // Click en el botón de envío
        Locator submitButton = page.locator("#contact-form-submit");
        submitButton.click();

        // Verificar el mensaje de error
        Locator errorMessage = page.locator("#contact-form-status");
        assertThat(errorMessage).hasText("Por favor, ingresa un correo electrónico válido");
    }

    @Test
    public void ContactTest() {
        page.navigate("http://localhost:8080/contact");

        // Verificar que los campos del formulario existen y son visibles
        Locator nameField = page.locator("#input-vaadin-text-field-9");
        Locator emailField = page.locator("#input-vaadin-email-field-10");
        Locator messageField = page.locator("#textarea-vaadin-text-area-11");

        assertThat(nameField).isVisible();
        assertThat(emailField).isVisible();
        assertThat(messageField).isVisible();

        // Llenar los campos del formulario
        nameField.fill("John Doe");
        emailField.fill("johndoe@example.com");
        messageField.fill("Este es un mensaje de prueba.");

        // Click en el botón de envío
        Locator submitButton = page.locator("#contact-form-submit");
        submitButton.click();

        // Verificar el mensaje de éxito
        Locator successMessage = page.locator("#contact-form-status");
        assertThat(successMessage).hasText("Mensaje enviado exitosamente");
    }
}
