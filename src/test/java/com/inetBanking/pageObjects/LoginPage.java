package com.inetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	// Identified all the webElements present on Login Page
	@FindBy(name ="uid")
	@CacheLookup
	WebElement txtuserName;
	
	@FindBy(name ="password")
	@CacheLookup
	WebElement txtpassword;
	
	@FindBy(name ="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath ="/html/body/div[3]/div/ul/li[15]/a")
	@CacheLookup
	WebElement btnLogout;
	
	//Action methods
	public void setUsername(String userName)
	{
		txtuserName.sendKeys(userName);
	}
	public void setPassword (String password)
	{
		txtpassword.sendKeys(password);
	}
	public void clickLoginButton()
	{
		btnLogin.click();
	}
	
	public void clickLogoutButton()
	{
		btnLogout.click();
	}




}
