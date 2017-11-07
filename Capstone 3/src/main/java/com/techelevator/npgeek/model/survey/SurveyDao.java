package com.techelevator.npgeek.model.survey;

import java.util.List;
import java.util.Map;

import com.techelevator.npgeek.model.park.Parks;

public interface SurveyDao {
	 public void addSurveyResult(Survey survey);
	 Map<Parks, Integer> getMostPopular();
}
