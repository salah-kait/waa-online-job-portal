package com.MIU.OnlineJob.Services.Search;

import com.MIU.OnlineJob.DataAccess.VacancyRepository;
import com.MIU.OnlineJob.Models.Vacancy;
import com.MIU.OnlineJob.Services.Search.Enums.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacancySearchServiceFactory implements SearchServiceFactory<Vacancy> {

    private VacancyElasticsSearch vacancyElasticsSearch;
    private VacancyDbSearch vacancyDbSearch;
    private VacancyRepository vacancyRepository;

    @Autowired
    private VacancySearchServiceFactory(VacancyElasticsSearch vacancyElasticsSearch, VacancyDbSearch vacancyDbSearch, VacancyRepository vacancyRepository) {
        this.vacancyElasticsSearch = vacancyElasticsSearch;
        this.vacancyDbSearch = vacancyDbSearch;
        this.vacancyRepository = vacancyRepository;
    }

    public SearchService<Vacancy> getService(SearchType type) {
        switch (type) {
            case Db:
                return new VacancyDbSearch(vacancyRepository);
            case Elastics:
                throw new UnsupportedOperationException();
                // new ElasticsSearch();
        }
        return null;
    }
}
