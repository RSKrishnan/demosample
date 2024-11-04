package org.bestBuy;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.POMSample.BestBuyMainPOM;
import org.POMSample.LoginPage;
import org.POMSample.SignUpPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainPage {
	WebDriver driver;

	@Test(priority = 1)
	public void BestBuyMainPage() throws IOException {
		PageFactory.initElements(driver, BestBuyMainPOM.class);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.bestbuy.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//driver.findElement(By.xpath("//div[@lang='en']//h4[contains(text(),'United States')]")).click();
		 BestBuyMainPOM.country.click();
		// BestBuyMainPOM.account.click();
		takeScreenShot("Mainpage.png");
		driver.findElement(By.xpath("//span[text()='Account']")).click();
	}

	// @Test(groups="SignUp")
	// public void signUp() throws IOException {
	// PageFactory.initElements(driver,SignUpPage.class );
	// SignUpPage.createAccnt.click();
	// SignUpPage.firstName.sendKeys("test");
	// SignUpPage.lastName.sendKeys("Test");
	// SignUpPage.email.sendKeys("test@example.com");
	// SignUpPage.psw1.sendKeys("TestExample@1234");
	// SignUpPage.psw2.sendKeys("TestExample@1234");
	// SignUpPage.phone.sendKeys("98798789879");
	// SignUpPage.submit.click();
	// takeScreenShot("SignUp.png");
	//}
	// WebDriverWait wb=new WebDriverWait(driver,Duration.ofSeconds(30));
	// wb.until(ExpectedConditions.visibilityOf(SignUpPage.firstName));

	@Test(groups = "Login", priority = 3)
	public void login() throws IOException {
		PageFactory.initElements(driver, LoginPage.class);
		LoginPage.login.click();
		LoginPage.usr.sendKeys("ramakrishnar013@gmail.com");
		LoginPage.psw.sendKeys("TestExample@1234");
		LoginPage.signIn.click();
		takeScreenShot("LoggedIn.png");
	}

	@Test(groups = "Menu", priority = 4)
	public void menuPage() {
		PageFactory.initElements(driver, BestBuyMainPOM.class);
		BestBuyMainPOM.menu.click();
		List<WebElement> links = BestBuyMainPOM.menuOptions;
		brokenLinkChecking(links);
	}
	
	@Test(groups = "Shop",priority = 6)
	public void shopping() throws IOException {
		driver.findElement(By.cssSelector("input#gh-search-input")).sendKeys("airpords", Keys.ENTER);
		driver.findElement(By.cssSelector("//a/img")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,750)");
		driver.findElement(By.xpath(
				"//button[contains(@class,'//*[@id=\"fulfillment-add-to-cart-button-57956397\"]/div/div/div[1]/button')]"))
				.click();
		takeScreenShot("Cart.png");
		
		driver.findElement(By.cssSelector("span.cart-label")).click();
		driver.findElement(By.xpath("//*[text()=\"Continue shopping\"]")).click();
		BestBuyMainPOM.menu.click();
		driver.findElement(By.xpath("//*[text()=\"Appliances\"]")).click();
		driver.findElement(By.xpath("//*[text()=\"Shop by Brand\"]")).click();
		driver.findElement(By.xpath("//*[text()=\"Samsung\"]")).click();
	}
	
	@Test(groups="bottomlink",priority = 5)
	public void bottomLink() {
		List<WebElement> links = driver.findElements(By.xpath("//*[@class=\"body-copy-sm mr-200\"]"));
		brokenLinkChecking(links);
	}

	public void takeScreenShot(String name) throws IOException {
		File sc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sc, new File("C:\\Automation\\BestBuy\\ScreenShot" + name));
	}
	//Need to check whether the same code can be used for navigation menu validation
	public void brokenLinkChecking(List links)
	{
		HttpURLConnection con = null;
		int responseCode = 200;
		String url = "";
	//	List<WebElement> links = driver.findElements(By.xpath("//*[@class=\"body-copy-sm mr-200\"]"));
		Iterator<WebElement> it = links.iterator();
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			try {
				con = (HttpURLConnection) (new URL(url).openConnection());
				con.setRequestMethod("HEAD");
				con.connect();
				responseCode = con.getResponseCode();
				if (responseCode >= 400) {
					System.out.println(url + "is a broken link");
				} else {
					System.out.println(url + "is a valid link");
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
