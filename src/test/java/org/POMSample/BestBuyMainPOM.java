package org.POMSample;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BestBuyMainPOM {

	@FindBy(xpath="//div[@lang='en']//h4[contains(text(),'United States')]")
	public static WebElement country;
	
	@FindBy(xpath="//span[text()='Account']")
	public static WebElement account;
	
	@FindBy(xpath="//*[text()=\"Menu\"]")
	public static WebElement menu;
	
	@FindBy(tagName="a")
	public static List<WebElement> menuOptions;
}
