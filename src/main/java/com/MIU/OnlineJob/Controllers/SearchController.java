package com.MIU.OnlineJob.Controllers;

import com.MIU.OnlineJob.Services.Search.VacancySearchServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MIU.OnlineJob.Exception.ResourceNotFoundException;
import com.MIU.OnlineJob.Models.Vacancy;
import com.MIU.OnlineJob.Payload.SearchResult;
import com.MIU.OnlineJob.Services.Search.SearchServiceFactory;
import com.MIU.OnlineJob.Services.Search.SearchService;
import com.MIU.OnlineJob.Services.Search.Enums.SearchType;

@RestController
@RequestMapping("/api/search")
public class SearchController {

	private SearchService<Vacancy> vacancySearch;
	private VacancySearchServiceFactory searchServiceFactory;

	@Autowired
	public SearchController(VacancySearchServiceFactory searchServiceFactory) {
		this.searchServiceFactory = searchServiceFactory;
		this.vacancySearch = searchServiceFactory.getService(SearchType.Db);
	}

	@GetMapping("/vacancy/{term}")
	SearchResult<Vacancy> getResult(@PathVariable String term) throws ResourceNotFoundException {

		return vacancySearch.Search(term);
	}
}
