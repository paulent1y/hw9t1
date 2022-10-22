package by.pavlunt1y;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class hwapp {
    public static void main(String[] args) throws Exception {
//        registrationTest(3);
//        loginTest(3);
        addToCart(1);
    }

    public static void registrationTest(int repeats) {
        for (int i = 0; i < repeats; i++) {
            String password = Util.generatePassword();
            String mail = Util.generateEmail();
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            ChromeDriver d = new ChromeDriver();
            d.manage().window().maximize();
            d.get("https://malkite.bg/en/");

            d.findElement(By.className("c-header__profile-register")).click();
            new Select(d.findElement(By.cssSelector("#CustomerBillingCountry"))).selectByValue("176");
            d.findElement(By.cssSelector("#CustomerEmail")).sendKeys(mail);
            d.findElement(By.cssSelector("#CustomerPassword")).sendKeys(password);
            d.findElement(By.cssSelector("#CustomerPasswordConfirm")).sendKeys(password);
            d.findElement(By.cssSelector("#CustomerBillingFirstName")).sendKeys("John");
            d.findElement(By.cssSelector("#CustomerBillingLastName")).sendKeys("Snow");
            d.findElement(By.cssSelector("#CustomerBillingPhone")).sendKeys("+1239450493");
            d.findElement(By.cssSelector("#CustomerBillingCity")).sendKeys("Warsaw");
            d.findElement(By.xpath("//input[@name='CustomerBillingAddress1']")).sendKeys("Somestreet 20");
            d.findElement(By.cssSelector("#c-privacy-policy__checkbox")).click();
            d.findElement(By.cssSelector("#registerButton")).click();

            if (!d.findElements(By.className("c-header__profile-logout")).isEmpty()) {
                Util.saveCredsToFile(mail, password);
                Util.getCredsFromFile();
                System.out.println("Registration successful with");
                System.out.println("mail: " + mail);
                System.out.println("pass: " + password);
            } else System.out.println("Registration unsuccessful");
            d.quit();
        }

    }

    public static void loginTest(int repeats) {
        for (int i = 0; i < repeats; i++) {
            String[] s = Util.getCredsFromFile().split(",");
            String mail = s[0];
            String pass = s[1];
            ChromeDriver d = new ChromeDriver();
            d.manage().window().maximize();
            d.get("https://malkite.bg/en/");
            d.findElement(By.className("c-header__profile-login")).click();
            d.findElement(By.cssSelector("#Email")).sendKeys(mail);
            d.findElement(By.cssSelector("#Password")).sendKeys(pass);
            d.findElement(By.cssSelector("#loginButton")).click();
            if (!d.findElements(By.className("c-header__profile-logout")).isEmpty()) {
                System.out.println("Login successful with");
                System.out.println("mail: " + mail);
                System.out.println("pass: " + pass);
            } else System.out.println("Register unsuccessful");

            d.quit();
        }
    }

    public static void addToCart(int repeats) {
        for (int i = 0; i < repeats; i++) {
            ChromeDriver d = new ChromeDriver();
            d.manage().window().maximize();
            d.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            d.get("https://magento.softwaretestingboard.com");
            d.findElement(By.xpath("//nav[@class=\"navigation\"]/ul/li[3]")).click();
            d.findElement(By.xpath("//dl[@id=\"narrow-by-list2\"]/dd/ol/li[1]/a")).click();
            List<WebElement> products = d.findElements(By.xpath("//li[@class=\"item product product-item\"]"));
            products.get(new Random().nextInt(products.size())).click();
            new WebDriverWait(d, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"swatch-option text\"]")));
            d.findElements(By.xpath("//*[@class=\"swatch-option text\"]")).get(0).click();
            d.findElements(By.xpath("//*[@class=\"swatch-option color\"]")).get(0).click();
            for (int j = 0; j < 2; j++) {
                d.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]")).click();
                new WebDriverWait(d, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"message-success success message\"]")));
            }
            d.findElement(By.xpath("//a[@class=\"action showcart\"]")).click();
            new WebDriverWait(d, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id=\"top-cart-btn-checkout\"]"))).click();
            new WebDriverWait(d, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tooltip\"]")));
//            new WebDriverWait(d, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable (By.xpath("//input[@id=\"customer-email\"")));
            d.findElement(By.xpath("//input[@id=\"customer-email\"]")).sendKeys(Util.generateEmail());
            new Select(d.findElement(By.xpath("//*[@name=\"country_id\"]"))).selectByValue("US");
            new Select(d.findElement(By.xpath("//*[@name=\"region_id\"]"))).selectByValue("1");
            d.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys("John");
            d.findElement(By.xpath("//input[@name=\"lastname\"]")).sendKeys("Smith");
            d.findElement(By.xpath("//input[@name=\"street[0]\"]")).sendKeys("Baker Street 221b");
            d.findElement(By.xpath("//input[@name=\"city\"]")).sendKeys("New York");
            d.findElement(By.xpath("//input[@name=\"postcode\"]")).sendKeys("12345-6789");
            d.findElement(By.xpath("//input[@name=\"telephone\"]")).sendKeys("+1239450229");
            d.findElement(By.xpath("//div[@id=\"checkout-shipping-method-load\"]/table/tbody/tr")).click();
            d.findElement(By.xpath("//div[@id=\"shipping-method-buttons-container\"]/div")).click();
//            d.findElement(By.xpath("//button[@class=\"action primary checkout\"]")).click();
            new WebDriverWait(d, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"action action-edit\"]")));
            d.findElement(By.xpath("//*[@class=\"action primary checkout\"]")).click();
//            d.quit();
        }
    }


    public static void w(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}