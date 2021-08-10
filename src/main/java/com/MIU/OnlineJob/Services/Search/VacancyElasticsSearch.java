package com.MIU.OnlineJob.Services.Search;

import com.MIU.OnlineJob.Models.Vacancy;
import com.MIU.OnlineJob.Payload.SearchResult;
import org.springframework.stereotype.Service;


@Service
 class VacancyElasticsSearch implements SearchService<Vacancy> {

	@Override
	public SearchResult<Vacancy> Search(String term) {
		// TODO Auto-generated meth
		return null;
	}

}
