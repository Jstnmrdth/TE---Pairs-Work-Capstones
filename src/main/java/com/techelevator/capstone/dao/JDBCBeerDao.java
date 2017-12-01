package com.techelevator.capstone.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.capstone.model.Beer;
import com.techelevator.capstone.model.Brewery;

@Component
public class JDBCBeerDao implements BeerDao {
	
	private JdbcTemplate jdbcTemplate;
	private BreweryDao breweryDao;
	
	@Autowired
	public JDBCBeerDao(BreweryDao breweryDao, DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.breweryDao = breweryDao;
	}

	@Override
	public List<Beer> getAllBeer(int breweryId) {
		List<Beer> beerList = new ArrayList<>();
		String sqlSelectBeers = "SELECT * FROM beer WHERE brewery_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectBeers, breweryId);
		while(results.next()) {
			Brewery thisBrewery = breweryDao.getBreweryByBreweryId(breweryId);
			Beer thisBeer = mapBeerToRow(results);
			beerList.add(thisBeer);
		}
		return beerList;
	}

	private Beer mapBeerToRow(SqlRowSet results) {
		Beer thisBeer = new Beer();
		thisBeer.setBreweryId(results.getInt("brewery_id"));
		thisBeer.setBeerType(results.getString("beer_type"));
		thisBeer.setDescription(results.getString("description"));
		thisBeer.setGlassType(results.getString("glass_type"));
		thisBeer.setAbv(results.getDouble("abv"));
		thisBeer.setIbu(results.getInt("ibu"));
		thisBeer.setName(results.getString("name"));
		thisBeer.setBeerId(results.getInt("beer_id"));
		Brewery thisBrewery = breweryDao.getBreweryByBreweryId(thisBeer.getBreweryId());
		thisBeer.setBrewery(thisBrewery);
		return thisBeer;
	}

	@Override
	public Beer getBeerByBeerId(int beerId) {
		String sqlSelectBeerById = "SELECT * FROM beer WHERE beer_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectBeerById, beerId);
		Beer beer = new Beer();
		
		if (results.next()) {
			beer = mapBeerToRow(results);
		}
		return beer;
	}

	@Override
	public void addBeer(int breweryId, String name, String beerType, String description,
			double abv, int ibu, String glassType) {
		String sqlAddBeer = "INSERT INTO beer (brewery_id, name, beer_type, abv, ibu, glass_type, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlAddBeer, breweryId, name, beerType, abv, ibu, glassType, description);
				
	}
	
	@Override
	public void deleteBeer(int beerId) {
		String sqlDeleteReview = "DELETE FROM review WHERE beer_id = ?";
		jdbcTemplate.update(sqlDeleteReview, beerId);
		String sqlDeleteBeer = "DELETE FROM beer WHERE beer_id = ?";
		jdbcTemplate.update(sqlDeleteBeer, beerId);
	}


}









