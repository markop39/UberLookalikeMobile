package com.example.taxidriver.data.dto;


import java.util.List;

public class PaginatedResponse<T> {

    private int totalCount;
    private List<T> results;

    public PaginatedResponse(int totalCount, List<T> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
