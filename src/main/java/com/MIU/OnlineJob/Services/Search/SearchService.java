package com.MIU.OnlineJob.Services.Search;

import com.MIU.OnlineJob.Payload.SearchResult;

public interface SearchService<T> {

	SearchResult<T> Search(String term);
}
