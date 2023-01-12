package com.github.samba.mohamed.project_action.service;

import com.github.samba.mohamed.project_action.model.SearchHistory;
import com.github.samba.mohamed.project_action.repository.SearchHistotyRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
@AllArgsConstructor
public class SearchHistoryService {
    private SearchHistotyRepository searchHistoryRepo;
    public void saveSearchHistory(String symbol,String name){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        SearchHistory h = new SearchHistory(symbol,name,formatter.format(date));
        searchHistoryRepo.save(h);
    }

    public List<SearchHistory> getSearchHistory(){
        return searchHistoryRepo.findAll();
    }
}
