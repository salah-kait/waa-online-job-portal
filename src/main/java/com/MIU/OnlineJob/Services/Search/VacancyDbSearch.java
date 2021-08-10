package com.MIU.OnlineJob.Services.Search;

import com.MIU.OnlineJob.DataAccess.VacancyRepository;
import com.MIU.OnlineJob.Models.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MIU.OnlineJob.Payload.SearchResult;

import java.util.List;

@Service
public class VacancyDbSearch implements SearchService<Vacancy> {

    private VacancyRepository repository;

    @Autowired
    public VacancyDbSearch(VacancyRepository repository) {
        this.repository = repository;
    }

    @Override
    public SearchResult<Vacancy> Search(String term) {


        List<Vacancy> result = repository.search(term);
        return new SearchResult<Vacancy>() {
            @Override
            public List<Vacancy> getResult() {
                return result;
            }

            @Override
            public int getCount() {
                return result.size();
            }
        };
    }

}
