package com.group18.asdc.dao;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.errorhandling.SavingSurveyException;
import com.group18.asdc.errorhandling.SurveyAlreadyPublishedException;

public interface SurveyDao {

	public SurveyMetaData getSavedSurvey(Course course);

	public boolean saveSurvey(SurveyMetaData surveyData) throws SavingSurveyException;

	public boolean isSurveyExists(Course course);

	public int createSurvey(Course course);

	public boolean isSurveyPublished(Course course);

	public boolean publishSurvey(SurveyMetaData surveyMetaData) throws SurveyAlreadyPublishedException;
}