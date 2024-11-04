package org.POMSample;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage {
	
	@FindBy(id="firstName")
	public static WebElement firstName;
	
	@FindBy(id="lastName")
	public static WebElement lastName;
	
	@FindBy(id="email")
	public static WebElement email;
	
	@FindBy(id="fld-p1")
	public static WebElement psw1;
	
	@FindBy(id="reenterPassword")
	public static WebElement psw2;
	
	@FindBy(id="phone")
	public static WebElement phone;
	
	@FindBy(xpath="(//*[text()=\"Create an Account\"])[2]")
	public static WebElement submit;

	@FindBy(xpath="//*[text()=\"Create Account\"]")
	public static WebElement createAccnt;
	
}
