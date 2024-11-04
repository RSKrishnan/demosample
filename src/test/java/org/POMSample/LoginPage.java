package org.POMSample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(css="input#fld-e")
	public static WebElement usr;
	
	@FindBy(css="input#fld-p1")
	public static WebElement psw;
	
	@FindBy(xpath="//*[text()='Sign In']")
	public static WebElement signIn;
	
	@FindBy(xpath="//*[text()=\"Sign In\"]")
	public static WebElement login;
	
}
