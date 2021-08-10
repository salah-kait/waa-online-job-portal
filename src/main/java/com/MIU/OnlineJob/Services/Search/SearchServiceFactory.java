package com.MIU.OnlineJob.Services.Search;

import com.MIU.OnlineJob.Services.Search.Enums.SearchType;


public interface SearchServiceFactory<T> {
    SearchService<T> getService(SearchType type);
}
