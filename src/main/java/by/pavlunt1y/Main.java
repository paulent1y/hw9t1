package by.pavlunt1y;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","F:\\projects\\java\\chromedriver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        WebElement cookies = driver.findElement(By.xpath("//button[@id=\"L2AGLb\"]"));
        cookies.click();
        WebElement searchField = driver.findElement(By.xpath("//input[@aria-label=\"Szukaj\"]"));
        searchField.click();
        searchField.sendKeys("zupa pho lodz");
        WebElement searchButton = driver.findElements(By.xpath("//input[@aria-label=\"Szukaj w Google\"]")).get(1);
        searchButton.click();
        driver.close();
    }
}