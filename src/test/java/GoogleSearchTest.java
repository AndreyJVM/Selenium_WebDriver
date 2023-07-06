import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

/**
 * Тест, заходит на главную страницу Google находит поиск по имени,
 * в поисковике вводим произволный текст
 * сравниваем наш текст с тем, что отображается в верхушке
 */

public class GoogleSearchTest {
    private WebDriver driver;
    private static final String GOOGLE_URL = "https://www.google.ru/";

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(GOOGLE_URL);
    }

    @Test
    public void testGoogleSearch() {
        WebElement element = driver.findElement(By.name("q"));
        element.clear();
        element.sendKeys("Selenium testing tools cookbook");
        element.submit();


        new WebDriverWait(driver, Duration.ofSeconds(10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase()
                        .startsWith("selenium testing tools cookbook");
            }
        });

        assertEquals("Selenium testing tools cookbook - Поиск в Google",
                driver.getTitle());
    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
    }
}