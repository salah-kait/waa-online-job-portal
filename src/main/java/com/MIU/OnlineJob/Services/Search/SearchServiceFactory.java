package com.MIU.OnlineJob.Services.Search;

import com.MIU.OnlineJob.DataAccess.VacancyRepository;
import com.MIU.OnlineJob.Models.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MIU.OnlineJob.Services.Search.Enums.SearchType;


public interface SearchServiceFactory<T> {
    SearchService<T> getService(SearchType type);
}
