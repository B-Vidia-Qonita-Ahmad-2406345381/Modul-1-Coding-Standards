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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class DeleteProductFunctionalTest {

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
    void deleteProduct_isDisplayedInProductList(ChromeDriver driver) {

        driver.get(baseUrl + "/product/create");

        driver.findElement(By.id("nameInput"))
                .sendKeys("Indomie");

        driver.findElement(By.id("quantityInput"))
                .sendKeys("50");

        driver.findElement(By.tagName("button"))
                .click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form button.btn-danger")));

        driver.findElement(By.cssSelector("form button.btn-danger")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Create Product")
        ));

        String afterDelete = driver.getPageSource();

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getPageSource());

        assertFalse(afterDelete.contains("Indomie"));
    }

}