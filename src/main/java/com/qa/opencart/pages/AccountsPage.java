package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constatnts.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accHeaders = By.cssSelector("div#content > h2");
	
	
	
	// page contructors....
		public AccountsPage(WebDriver driver)
		{	
			this.driver = driver;
			eleUtil = new ElementUtil(this.driver);
		}
		
	// page actions 
		
		public String getAccPageTitle()
		{
			String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_DEFAUTT_WAIT);
			System.out.println("Account page Title :- " + title);
			return title;
		}
		
		public String getAccPageURL()
		{
			String URL = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAUTT_WAIT);
			System.out.println("Account page URL :- " + URL);
			return URL;
		}
	
		public boolean isLogoutLinkExist() 
		{
			return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
		}
			
		public void logout() 
		{
			if (isLogoutLinkExist()) 
			{
				eleUtil.doClick(logoutLink);
			}
		}
		
		public boolean isSearchFiledExist() 
		{
			return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
		}
		
		public List<String>  getAccountsHeaders()
		{
			List<WebElement> headersList = eleUtil.waitForVisibilityOfElements(accHeaders, AppConstants.MEDIUM_DEFAUTT_WAIT);
			List<String> headerValList = new ArrayList<String>();
			for(WebElement e : headersList)
			{
				String text = e.getText();
				headerValList.add(text);
			}
			return headerValList;
		}
		
		public SearchResultsPage doSearch(String searchKey)
		{
			eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAUTT_WAIT).clear();
			eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAUTT_WAIT).sendKeys(searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}
	
}