package com.test.objectRepository;

import java.time.Duration;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;


public class DistrictEventsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    
    //Constructor
    public DistrictEventsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    
    //Locator
    private By eventCardLocator = By.xpath("//*[@id='page-content']/section/div[1]/div[1]/div[2]/div[3]/div[2]/a");
    private final By dateLoc     = By.xpath(".//div/div/span[1]");
    private final By nameLoc     = By.xpath(".//div/div/h5");
    private final By locationLoc = By.xpath(".//div/div/span[2]");
    private final By priceLoc    = By.xpath(".//div/div/span[3]");

    //Actions
    public void scrollToEnd() throws InterruptedException {
    	//Scrolls till the end of the page
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

        while (true) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);
            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) {
                System.out.println("✅ Reached bottom of the page.");
                break;
            }
            lastHeight = newHeight;
        }
    }

    public List<Event> fetchAndSortByPrice() throws InterruptedException {
        // 1) wait for initial batch
        wait.until(ExpectedConditions.presenceOfElementLocated(eventCardLocator));

        // 2) infinite scroll
        scrollToEnd();

        // 3) re-find after scroll
        List<WebElement> cards = driver.findElements(eventCardLocator);
        List<Event> results = new ArrayList<>();

        for (WebElement card : cards) {
            String rawPrice = card.findElement(priceLoc).getText().trim();
            int price = parsePrice(rawPrice);
            if (price < 0) continue;

            String date     = card.findElement(dateLoc).getText();
            String name     = card.findElement(nameLoc).getText();
            String location = card.findElement(locationLoc).getText();
            results.add(new Event(date, name, location, price));
        }

        // 4) sort ascending by price
        Collections.sort(results, Comparator.comparingInt(Event::getPrice));
        return results;
    }

    private int parsePrice(String txt) {
    	//Parsing price from events
        if (txt == null || txt.isEmpty()) return -1;
        txt = txt.toLowerCase();
        if (txt.contains("free")) return 0;
        txt = txt.replaceAll("[\\$,₹,]", "").replace("onwards", "").trim();
        try { return Integer.parseInt(txt); }
        catch (NumberFormatException e) { return -1; }
    }

    public static class Event {
        private final String date, name, location;
        public String getDate() {
			return date;
		}
		public String getName() {
			return name;
		}
		public String getLocation() {
			return location;
		}
		private final int price;
        public Event(String date, String name, String location, int price) {
            this.date = date; this.name = name;
            this.location = location; this.price = price;
        }
        public int getPrice() { return price; }
        @Override
        public String toString() {
            return String.format("Date: %s | Name: %s | Location: %s | Price: %d",
                                 date, name, location, price);
        }
    }
}
