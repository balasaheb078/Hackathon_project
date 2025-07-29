package com.test.objectRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.resources.Movie;

public class DistrictMoviePage {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	//Constructor
	public DistrictMoviePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	@FindBy(xpath = "//a[text()='Movies'][1]") WebElement moviesBtn;
	
	@FindBy(xpath = "//*[@id='page-content']/section/div[1]/div[1]/div[2]/div[1]/div[2]/a") List<WebElement> movies;
	
	//Actions
	public void clickMoviesPage() {
		//Moves to Movies Page
		wait.until(ExpectedConditions.elementToBeClickable(moviesBtn)).click();
	}
	
	public List<Movie> getMovieLanguage() throws Exception{
		//Gets language, rating and movie name
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='page-content']/section/div[1]/div[1]/div[2]/div[1]/div[2]/a")));
		
		List<Movie> movieList = new ArrayList<>();
		
		//Prints total no of movies
		System.out.println("Total movies: " + movies.size());
		
		for(WebElement movie:movies) {
			String movieInfo = movie.getText();
			String[] movieParts = movieInfo.split("\\R");
			
			String movieName = movieParts[0];
			String[] movieParts2 = movieParts[1].split(" ");
			String rating = movieParts2[0].trim();
			String language = movieParts2[2].trim();
			
			movieList.add(new Movie(movieName, language, rating));	
		}
		
		return movieList;
	}
	
	public void printMovieLanguage(List<Movie> movieList) {
		//Prints all the movies
		for(Movie mov:movieList) {
			System.out.println(mov);
		}
	}
}
