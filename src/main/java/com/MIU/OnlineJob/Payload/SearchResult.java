package com.MIU.OnlineJob.Payload;

import java.util.List;

public interface SearchResult<T> {
	List<T> getResult();

	int getCount();
}
