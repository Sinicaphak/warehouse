package com.p1.service.inter;

import com.p1.pojo.SearchHistory;

public interface SearchHistoryService {

    int insertSearchHistory(int userId,int rid);

    int deleteSearchHistoryBySearchHistoryId(int searchHistoryId);

    SearchHistory selectSearchHistoryById(int searchHistoryId);

}
