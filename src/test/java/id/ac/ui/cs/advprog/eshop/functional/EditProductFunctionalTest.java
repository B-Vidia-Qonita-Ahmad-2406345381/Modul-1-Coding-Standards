package id.ac.ui.cs.advprog.eshop.functional;

import id.ac.ui.cs.advprog.eshop.service.ProductService;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class EditProductFunctionalTest {

    @Autowired
    private ProductService service;

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
        service.clear();
    }

    @Test
    void editProduct_isDisplayedInProductList(ChromeDriver driver) {

        driver.get(baseUrl + "/product/list");

        driver.findElement(By.linkText("Create Product")).click();

        driver.findElement(By.id("nameInput"))
                .sendKeys("Indomie");

        driver.findElement(By.id("quantityInput"))
                .sendKeys("50");

        driver.findElement(By.tagName("button"))
                .click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Edit")));

        driver.findElement(By.linkText("Edit")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("productName")));

        driver.findElement(By.name("productName")).clear();
        driver.findElement(By.name("productName")).sendKeys("Mie Sedap");

        driver.findElement(By.name("productQuantity")).clear();
        driver.findElement(By.name("productQuantity")).sendKeys("55");

        driver.findElement(By.tagName("button"))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Edit")));

        String pageSource = driver.getPageSource();

        assertTrue(pageSource.contains("Mie Sedap"));
        assertTrue(pageSource.contains("55"));
    }
    @Test
    void editProduct_productNotFound(ChromeDriver driver) {
        service.clear();

        driver.get(baseUrl + "/product/edit?productId=INVALID_ID");

        String pageSource = driver.getPageSource();

        assertTrue(pageSource.contains("Product' List"));
    }
}