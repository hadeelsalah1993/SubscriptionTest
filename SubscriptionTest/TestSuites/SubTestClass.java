package TestSuites;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class SubTestClass {
	 WebDriver driver;
	 @BeforeTest
	 
 public void setUp() {
	        // Set up WebDriver
	        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
	        driver = new ChromeDriver();
	        driver2=new  ChromeDriver();
	        driver.manage().window().maximize();
	    }
	
  @Test
  public void validateSubscriptionPackages() {
	  // Navigate to the URL  
      driver.get("https://subscribe.stctv.com/");

      // Validate subscription packages for SA
      validatePackagesForCountry("Saudi Arabia", "SAR");

      // Validate subscription packages for Kuwait
      validatePackagesForCountry("Kuwait", "KWD");

      // Validate subscription packages for Bahrain
      validatePackagesForCountry("Bahrain", "BHD");
  }
  // Helper method to validate subscription packages for a specific country
  private void validatePackagesForCountry(String country, String currency) 
  {
      WebElement countryElement = driver.findElement(By.xpath("//a[text()='" + country + "']"));
      countryElement.click();
      
      WebElement typeElement = driver.findElement(By.xpath("//h3[@class='type']"));
      String type = typeElement.getText();
      
      WebElement priceElement = driver.findElement(By.xpath("//h3[@class='price']"));
      String price = priceElement.getText();
      
      WebElement currencyElement = driver.findElement(By.xpath("//span[@class='currency']"));
      String actualCurrency = currencyElement.getText();

      // Assertion for type, price, and currency
      Assert.assertNotNull(type, "Type not found for " + country);
      Assert.assertNotNull(price, "Price not found for " + country);
      Assert.assertNotNull(actualCurrency, "Currency not found for " + country);
      Assert.assertEquals(actualCurrency, currency, "Currency mismatch for " + country);
  }
  
  @AfterTest
  public void tearDown() {
      // Close the WebDriver
      driver.quit();
  }
  }
}
