package com.p1.service;

import com.p1.dao.SearchHistoryMapper;
import com.p1.pojo.SearchHistory;
import com.p1.service.inter.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("searchHistoryServiceImpl")
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    @Override
    public int insertSearchHistory(int userId,int rid) {
        SearchHistory searchHistory=new SearchHistory();
        searchHistory.setRid(rid);
        searchHistory.setUserId(userId);
        return searchHistoryMapper.insert(searchHistory);
    }

    @Override
    public int deleteSearchHistoryBySearchHistoryId(int searchHistoryId) {
        return searchHistoryMapper.deleteByPrimaryKey(searchHistoryId);
    }

    @Override
    public SearchHistory selectSearchHistoryById(int searchHistoryId) {
        return searchHistoryMapper.selectByPrimaryKey(searchHistoryId);
    }
}
