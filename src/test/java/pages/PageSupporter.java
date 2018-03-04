package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * 
 * @author         :VNatarajan
 * @since          :Sep 20, 2017
 * @filename       :PageSupporter.java
 * @version		   :
 * @description    :The class to provide to common tests features.This needs to be extended by all page classes
 */
public class PageSupporter {
	WebDriverWait wait;
	protected WebDriver driver;
	public PageSupporter(WebDriver driver) {
		this.driver = driver;
	}
	public boolean waitFor(WebElement element,int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	
	public boolean waitFor(List<WebElement> elements,int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public void timeout(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	
	public void wait(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void waitUntil(By element,int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	
	public void pageLoadWait(WebDriver driver,int seconds) {				

		JavascriptExecutor je = (JavascriptExecutor) driver;
		String page_state="";
		System.out.println("Waiting for the page to load...");

		while(((!page_state.equalsIgnoreCase("complete"))||(!page_state.contains("complete")))&&(seconds<20)){

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			page_state=(String) je.executeScript("return document.readyState");
			System.out.println("..."+page_state+"...");
			System.out.println(((!page_state.equalsIgnoreCase("complete"))||(!page_state.contains("complete")))&&(seconds>0));
			seconds--;
		}		
		
	}
}
