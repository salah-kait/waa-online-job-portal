package com.MIU.OnlineJob.Payload;

import java.util.List;

import com.MIU.OnlineJob.Models.Vacancy;

public class VacancySearchResult implements SearchResult<Vacancy> {

	private List<Vacancy> result;
	private int totalCount;

	@Override
	public List<Vacancy> getResult() {
		return result;
	}

	@Override
	public int getCount() {
		return totalCount;
	}

}
