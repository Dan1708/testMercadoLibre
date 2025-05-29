import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MercadoLibreTest {

    @Test
    public void testBusquedaPlaystation5() throws InterruptedException {
        String s = System.setProperty("webdriver.chrome.driver", "C:\\Users\\danir\\IdeaProjects\\selenium_basic\\src\\test\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        // 1. Entrar al sitio
        driver.get("https://www.mercadolibre.com");

        // 2. Seleccionar país: México
        driver.findElement(By.xpath("//nav/ul/li/a[contains(.,'México')]")).click();

        // Esperar un poco para que cargue
        Thread.sleep(2000);

        // 3. Buscar "playstation 5"
        WebElement searchBox = driver.findElement(By.name("as_word"));
        searchBox.sendKeys("playstation 5");
        searchBox.submit();

        Thread.sleep(3000);

        // 4. Filtrar por "Nuevo"
        driver.findElement(By.xpath("//span[contains(text(),'Nuevo')]")).click();
        Thread.sleep(3000);

        // 5. Filtrar por ubicación "Cdmx"
        driver.findElement(By.xpath("//span[contains(text(),'Distrito Federal')]")).click();
        Thread.sleep(3000);

        // 6. Ordenar por "Mayor precio"
        WebElement sortMenu = driver.findElement(By.className("andes-dropdown__trigger"));
        sortMenu.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[@class='andes-list__item']//span[contains(text(),'Mayor precio')]")).click();
        Thread.sleep(4000);

        // 7. Obtener nombre y precio de los primeros 5 productos
        List<WebElement> titles = driver.findElements(By.cssSelector(".ui-search-item__title"));
        List<WebElement> prices = driver.findElements(By.cssSelector(".andes-money-amount__fraction"));

        System.out.println("Primeros 5 productos de la búsqueda:");
        for (int i = 0; i < 5 && i < titles.size() && i < prices.size(); i++) {
            System.out.println((i + 1) + ". " + titles.get(i).getText() + " - $" + prices.get(i).getText());
        }

        // 8. Cerrar navegador
        driver.quit();
    }
}
