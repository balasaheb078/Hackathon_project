package com.test.resources;

//Used for adding movie languages to a list
public class Movie {

	public String movieName;
	public String movieLanguage;
	public String rating;
	
	public Movie(String movieName, String movieLanguage, String rating) {
		this.movieName = movieName;
		this.movieLanguage = movieLanguage;
		this.rating = rating;
	}
	
	public String getMovieName() {
		return this.movieName;
	}
	
	public String getMovieLanguage() {
		return this.movieLanguage;
	}
	
	public String getRating() {
		return this.rating;
	}
	
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "Movie Name: " + movieName + ", Language: " + movieLanguage + ", Rating: " + rating;
	}
}
