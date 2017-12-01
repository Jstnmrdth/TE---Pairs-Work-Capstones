package com.techelevator.capstone.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techelevator.capstone.model.BeerReview;
import com.techelevator.capstone.model.Review;

@Component
public interface ReviewDao {
	public List<Review> getAllReviews();
	public List<Review> getReviewsByBeerId(int beerId);
	public List<BeerReview> getBeerReviews();
	public void postReview(int userId, int beerId, String description, int rating);

}
