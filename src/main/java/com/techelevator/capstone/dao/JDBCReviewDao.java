package com.techelevator.capstone.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.capstone.model.Beer;
import com.techelevator.capstone.model.BeerReview;
import com.techelevator.capstone.model.Review;
import com.techelevator.capstone.model.User;

@Component
public class JDBCReviewDao implements ReviewDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCReviewDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Review> getAllReviews() {
		String sqlGetReviews = "SELECT review_id, review.beer_id, review.user_id, username, review.description reviewDescription, glass_type, abv, ibu, rating, beer.brewery_id, name, beer.description beerDescription, beer.beer_type FROM review "
								+ "JOIN users ON users.user_id = review.user_id JOIN beer ON beer.beer_id = review.beer_id GROUP BY review.beer_id, beer.beer_id, users.username, review_id, beer.name, beer.description ORDER BY rating desc";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReviews);
		List<Review> reviewList = new ArrayList<>();
		while(results.next()) {
			reviewList.add(mapReviewToRow(results));
		}
		return reviewList;
	}
	
	@Override
	public List<BeerReview> getBeerReviews(){
		String sqlGetBeerReviews = "SELECT AVG(rating) avg_rating, username, beer.beer_id, glass_type, abv, ibu, beer.brewery_id, name, beer.description beerDescription, beer.beer_type FROM review "
				+ "JOIN users ON users.user_id = review.user_id JOIN beer ON beer.beer_id = review.beer_id GROUP BY beer.beer_id, users.username ORDER BY avg_rating desc";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetBeerReviews);
		List<BeerReview> beerReviewList = new ArrayList<>();
		while(results.next()) {
			beerReviewList.add(mapBeerReviewToRow(results));
		}
		
		return beerReviewList;
	}
	
	@Override
	public List<Review> getReviewsByBeerId(int beerId) {
		String sqlGetBeerReviews = "SELECT review_id, review.user_id, username, review.rating, review.description reviewDescription, beer.beer_id, glass_type, abv, ibu, beer.brewery_id, name, beer.description beerDescription, beer.beer_type FROM review " +
									"JOIN beer ON beer.beer_id = review.beer_id " + 
									"JOIN users ON users.user_id = review.user_id WHERE beer.beer_id = ? ORDER BY review.rating desc, users.username";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetBeerReviews, beerId);
		List<Review> reviewList = new ArrayList<>();
		while(results.next()) {
			reviewList.add(mapReviewToRow(results));
		}
		return reviewList;
	}

	private BeerReview mapBeerReviewToRow(SqlRowSet results) {
		Beer thisBeer = new Beer();
		thisBeer.setBeerId(results.getInt("beer_id"));
		thisBeer.setBeerType(results.getString("beer_type"));
		thisBeer.setDescription(results.getString("beerDescription"));
		thisBeer.setBreweryId(results.getInt("brewery_id"));
		thisBeer.setAbv(results.getDouble("abv"));
		thisBeer.setGlassType(results.getString("glass_type"));
		thisBeer.setIbu(results.getInt("ibu"));
		thisBeer.setName(results.getString("name"));
		
		BeerReview thisBeerReview = new BeerReview();
		thisBeerReview.setBeer(thisBeer);
		thisBeerReview.setAverageRating(results.getDouble("avg_rating"));
		return thisBeerReview;
	}

	private Review mapReviewToRow(SqlRowSet results) {
		Beer thisBeer = new Beer();
		thisBeer.setBeerId(results.getInt("beer_id"));
		thisBeer.setBeerType(results.getString("beer_type"));
		thisBeer.setDescription(results.getString("beerdescription"));
		thisBeer.setBreweryId(results.getInt("brewery_id"));
		thisBeer.setAbv(results.getDouble("abv"));
		thisBeer.setGlassType(results.getString("glass_type"));
		thisBeer.setIbu(results.getInt("ibu"));
		thisBeer.setName(results.getString("name"));
		
		User thisUser = new User();
		thisUser.setUsername(results.getString("username"));
		
		Review thisReview = new Review();
		thisReview.setUser(thisUser);
		thisReview.setBeer(thisBeer);
		thisReview.setReviewId(results.getInt("review_id"));
		thisReview.setUserId(results.getInt("user_id"));
		thisReview.setReviewDescription(results.getString("reviewdescription"));
		thisReview.setRating(results.getInt("rating"));
		return thisReview;
	}

	@Override
	public void postReview(int userId, int beerId, String description, int rating) {
		String sqlPostReview = "INSERT INTO review(beer_id, user_id, description, rating) VALUES (?,?,?,?)";
		jdbcTemplate.update(sqlPostReview, beerId, userId, description, rating);
		
	}

}
