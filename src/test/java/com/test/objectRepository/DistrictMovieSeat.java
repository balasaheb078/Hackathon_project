// File: DistrictMovieSeat.java
package com.test.objectRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.factory.BaseClass;

public class DistrictMovieSeat {
    private WebDriver driver;
    private WebDriverWait wait;
   
    //Locators
    private By moviesMenuLocator       = By.xpath("//a[text()='Movies'][1]");
    private By availableSeatsLocator   = By.xpath("//*[@class='available']");
    private By seatsCountHeaderLocator = By.xpath("//h3[text()='Seats']");

    //Constructor
    public DistrictMovieSeat(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait   = wait;
        PageFactory.initElements(driver, this);
    }

    //Actions
    public void openMoviesSection() {
    	//Opens the movie page
        wait.until(ExpectedConditions.elementToBeClickable(moviesMenuLocator))
            .click();
        BaseClass.getLogger().info("Opened Movies section");
    }

    public void selectMovie(String movieName) {
    	//Select the given movie name
    	String movieName_str = "//h5[text()="+movieName+"]";
        By movieLocator = By.xpath(movieName_str);
        wait.until(ExpectedConditions.elementToBeClickable(movieLocator))
            .click();
        BaseClass.getLogger().info("Selected movie: " + movieName);
    }

    public void selectDate(String date) {
    	//Selects the date of show
    	String moviedate_str = "//div[text()="+date+"]";
        By dateLocator = By.xpath(moviedate_str);
        wait.until(ExpectedConditions.elementToBeClickable(dateLocator))
            .click();
        BaseClass.getLogger().info("Selected date: " + date);
    }

    public void selectTimeSlot(String timeSlot) {
    	//Selects the time slot for show
    	String movieTime_str = "//div[text()="+timeSlot+"]";
        By timeLocator = By.xpath(movieTime_str);
        wait.until(ExpectedConditions.elementToBeClickable(timeLocator))
            .click();
        BaseClass.getLogger().info("Selected time slot: " + timeSlot);
    }

    // — Seat Counts & Actions —

    /**
     * Reads "Seats (n)" and extracts the integer n.
     */
    public int getSelectedSeatsCount() {
        String raw = wait
            .until(ExpectedConditions.visibilityOfElementLocated(seatsCountHeaderLocator))
            .getText();            // e.g. "Seats (5)"
        String digits = raw.replaceAll("\\D+", "");
        return Integer.parseInt(digits);
    }

    /**
     * Counts how many seats are currently clickable/available.
     */
    public int getAvailableSeatsCount() {
        List<WebElement> seats = wait
            .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(availableSeatsLocator));
        return seats.size();
    }

    /**
     * Randomly picks n unique seats from the current availability.
     * Uses fresh lookup each time to avoid stale‐element issues.
     *
     * @param n the number of seats to select
     * @return true if exactly n seats end up selected
     */
    public boolean selectRandomSeats(int n) {
        List<WebElement> seats = wait
            .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(availableSeatsLocator));

        int total = seats.size();
        if (total < n) {
            throw new IllegalArgumentException(
                String.format("Requested %d seats but only %d available", n, total));
        }

        Set<Integer> chosen = new HashSet<>();
        Random rnd = new Random();

        while (chosen.size() < n) {
            int idx = rnd.nextInt(total);  // 0..total-1
            if (chosen.add(idx)) {
                WebElement seat = seats.get(idx);
                wait.until(ExpectedConditions.elementToBeClickable(seat)).click();
                BaseClass.getLogger().info("Clicked seat #" + idx);
            }
        }

        // verify actual count matches
        int actual = getSelectedSeatsCount();
        BaseClass.getLogger().info(
            String.format("Expected to select %d seats; UI reports %d", n, actual)
        );
        return actual == n;
    }

    // — High‐Level Flow (Optional) —

    /**
     * Convenience method for booking flow in one shot.
     */
    public void bookRandomSeats(String movie, String date, String time, int seatsToBook) {
        openMoviesSection();
        selectMovie(movie);
        selectDate(date);
        selectTimeSlot(time);

        int before = getAvailableSeatsCount();
        BaseClass.getLogger().info("Seats available before selection: " + before);

        boolean ok = selectRandomSeats(seatsToBook);
        if (ok) {
            BaseClass.getLogger().info("✓ Successfully selected " + seatsToBook + " seats.");
        } else {
            BaseClass.getLogger().warn("✗ Seat selection mismatch.");
        }
        BaseClass.getLogger().info("---------------------------------------------------");
    }
}
