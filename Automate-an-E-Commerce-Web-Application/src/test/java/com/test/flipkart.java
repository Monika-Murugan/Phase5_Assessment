package com.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.awt.Toolkit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class flipkart {
	
	WebDriver chrome;
	String url;
	
  @Test(priority = 1)
  public void loadTime() throws Exception {
	  
	  JavascriptExecutor js=(JavascriptExecutor)chrome;
	  chrome.get("https://www.flipkart.com/");Thread.sleep(2000);
	  long navstart=(Long) js.executeScript("return window.performance.timing.navigationStart");
	  long respstart=(Long) js.executeScript("return window.performance.timing.responseStart");
	  long domcomp=(Long) js.executeScript("return window.performance.timing.domComplete");
	  long backendper=respstart-navstart;
	  long frontendper=domcomp-respstart;
	 System.out.println("backend performance : "+ backendper);
	 System.out.println("frontend performance : "+frontendper);
	  
  }
  
  @Test(priority = 2)
  public void search() throws Exception
  {
	  chrome.get("https://www.flipkart.com");Thread.sleep(2000);
	  WebElement cancle= chrome.findElement(By.cssSelector("button[class='_2KpZ6l _2doB4z']"));
	  cancle.click();Thread.sleep(2000);
	  WebElement searchbar= chrome.findElement(By.name("q"));
	  searchbar.sendKeys("iphone 13");Thread.sleep(2000);
	  chrome.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  WebElement searchbuton= chrome.findElement(By.cssSelector("button[class='L0Z3Pu']"));
	  searchbuton.click();Thread.sleep(2000);
	  By load = By.cssSelector("#container > div > div._36fx1h._6t1WkM._3HqJxg > div._1YokD2._2GoDe3 > div:nth-child(2) > div:nth-child(9) > div > div");
		long start = System.currentTimeMillis();
		chrome.findElement(load).click();
		long finish = System.currentTimeMillis();
		long totalTime = finish - start;
		System.out.println("\nTime to load page in milliseconds: " + totalTime);
	  this.url=chrome.getCurrentUrl();
	  
	  
  }
  
  @Test(priority = 3)
  public void scroll() throws Exception
  {
	  	chrome.get(this.url);Thread.sleep(2000);
	  
		JavascriptExecutor scrollBarPresent = (JavascriptExecutor) chrome;
		Boolean test = (Boolean) (scrollBarPresent.executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;"));
		if (test == true) {
			System.out.println("Scrollbar is present.");
		} else if (test == false){
			System.out.println("Scrollbar is not present.");
		}
	
  }
  
	
/*&
  @Test(priority=4)
  public void contentRefresh() 
  {
	  chrome.get(this.url);
	  //chrome.get("https://www.flipkart.com");
	  long startTime = System.currentTimeMillis();
	  JavascriptExecutor refresh = (JavascriptExecutor) chrome;
	  refresh.executeScript("window.scrollBy(0,1000)", "");
	  new WebDriverWait(chrome, Duration.ofMinutes(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='CXW8mj']")));
	  long endTime = System.currentTimeMillis();
	  long totalTime = endTime - startTime;
	  System.out.println(" Load Time after scrolling " + totalTime);
	 
	  
  }*/
  
  @Test(priority=5)
  public void imageDisplay() throws Exception
  {
	  chrome.get(this.url);Thread.sleep(2000);
	  WebElement image = chrome.findElement(By.xpath("//div[@class='CXW8mj']"));     
      if (image.isDisplayed()) 
      {
          System.out.println("before scrolling the image in not present"); 
          
      }
      	System.out.println("scrolling");
    	  Thread.sleep(2000);
    	  JavascriptExecutor scroll = (JavascriptExecutor) chrome;
    	  scroll.executeScript("window.scrollBy(0,710)");
          WebElement img1 = chrome.findElement(By.xpath("//div[@class='CXW8mj']")); Thread.sleep(2000);
          if(img1.isDisplayed()) {
        	  System.out.println("after scrolling the image is present"); 
  
          }  
  }
  
  @Test(priority = 6)
  public void bottomScroll() throws Exception
  {
	  chrome.get(this.url);Thread.sleep(2000);
	  chrome.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);Thread.sleep(2000);
	  try {
		((JavascriptExecutor) chrome).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		System.out.println("scrolled to bottom");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("did not scroll to bottom");
	}
//      System.out.println(s);
  }
  
  
  @Test(priority = 8)
  public void diffBrowChrome() throws Exception
  {
	 
	  chrome.get("https://www.flipkart.com");Thread.sleep(2000);
	  System.out.println("***********chrome************");
	  Dimension d= chrome.manage().window().getSize();Thread.sleep(2000);
	  System.out.println( "height : "+d.getHeight() +"\n width : "+d.getWidth());Thread.sleep(2000);
	  chrome.manage().window().setSize(new Dimension(702, 613));Thread.sleep(2000);
	  d= chrome.manage().window().getSize();Thread.sleep(2000);
	  System.out.println("after changing resoulution");
	  System.out.println( "height : "+d.getHeight() +"\n width : "+d.getWidth());
  }
  
  @Test(priority = 7)
  public void imgHeight() throws Exception
  {
	  chrome.get("https://www.flipkart.com");Thread.sleep(2000);
	 // WebElement cancle= chrome.findElement(By.cssSelector("button[class='_2KpZ6l _2doB4z']"));
	  //cancle.click();
	  java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  int width = (int) screenSize.getWidth();
	  int height = (int) screenSize.getHeight();
	  System.out.println("Resoultion of the device :  Screen  width "+ width+" Screen Height "+height );
	  chrome.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  int wid=chrome.findElement(By.cssSelector("img[class='_396cs4 _3exPp9']")).getSize().getWidth();Thread.sleep(2000);
      int h=chrome.findElement(By.cssSelector("img[class='_396cs4 _3exPp9']")).getSize().getHeight();Thread.sleep(2000);
      System.out.println("Resoultion of the image :  Image  width "+ wid+" Image Height "+h );
	  WebElement img = chrome.findElement(By.cssSelector("img[class='_396cs4 _3exPp9']"));   Thread.sleep(2000);  
	  if(img.isDisplayed()){
		  if (h<height)
      		{
          		System.out.println("Image is visible till the screen height itself"); 
      		}
	  }
	 
  }
  
  
  
  
  @Test(priority = 9)
  public void diffBrowEdge() throws Exception
  {
	  System.setProperty("webdriver.edge.driver", "F:\\Phase_5\\msedgedriver.exe");
	  WebDriver edge= new EdgeDriver();Thread.sleep(2000);
	  edge.get("https://www.flipkart.com");Thread.sleep(2000);
	  System.out.println("***********edge************");
	  Dimension d= edge.manage().window().getSize();Thread.sleep(2000);
	  System.out.println( "height : "+d.getHeight() +"\n width : "+d.getWidth());Thread.sleep(2000);
	  edge.manage().window().setSize(new Dimension(702, 613));Thread.sleep(2000);
	  d= edge.manage().window().getSize();Thread.sleep(2000);Thread.sleep(2000);
	  System.out.println("after changing resoulution");
	  System.out.println( "height : "+d.getHeight() +"\n width : "+d.getWidth());
	
  }
  
  
  @BeforeMethod
  public void beforeMethod() throws Exception {
	  chrome.manage().window().maximize();Thread.sleep(2000);
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver","F:\\Phase_5\\chromedriver.exe");
	  chrome= new ChromeDriver();
  }

  @AfterClass
  public void afterClass() {
  }

}
