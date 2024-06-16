import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MovieViewTest {

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
    public void movieTest() {

        page.navigate("http://localhost:8080/rate-movie");

        Locator movieNameField = page.locator("#input-vaadin-text-field-14");
        Locator autorField = page.locator("#input-vaadin-text-field-15");
        Locator radioButton5 = page.locator("#input-vaadin-radio-button-21");

        movieNameField.fill("Inception");
        autorField.fill("Christopher Nolan");
        radioButton5.click();

        page.locator("vaadin-button").getByText("Enviar").click();

        Locator successMessage = page.locator("text=Película calificada exitosamente");
        assertThat(successMessage).isVisible();
    }

    @Test
    public void emptyFieldsTest() {
        page.navigate("http://localhost:8080/rate-movie");

        // No llenar ningún campo y hacer clic en Enviar
        page.locator("vaadin-button").getByText("Enviar").click();

        // Verificar el mensaje de error
        Locator errorMessage = page.locator("#rate-movie-status");
        assertThat(errorMessage).hasText("Por favor, completa todos los campos");
    }

    @Test
    public void fieldsClearAfterSubmitTest() {
        page.navigate("http://localhost:8080/rate-movie");

        // Llenar los campos del formulario
        page.locator("#input-vaadin-text-field-14").fill("Inception");
        page.locator("#input-vaadin-text-field-15").fill("Christopher Nolan");
        page.locator("#input-vaadin-radio-button-21").click(); // Seleccionar la calificación

        // Hacer clic en Enviar
        page.locator("vaadin-button").getByText("Enviar").click();

        // Verificar que los campos se han limpiado
        assertThat(page.locator("#input-vaadin-text-field-14")).hasValue("");
        assertThat(page.locator("#input-vaadin-text-field-15")).hasValue("");
        assertFalse(page.locator("#input-vaadin-radio-button-21").isChecked());
    }
}
