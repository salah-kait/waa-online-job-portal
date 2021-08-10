package com.MIU.OnlineJob.Payload.Response;

import java.util.List;

import com.MIU.OnlineJob.Models.Vacancy;
import com.MIU.OnlineJob.Payload.Requests.SearchResult;

public class VacancySearchResultResponse implements SearchResult<Vacancy> {

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
