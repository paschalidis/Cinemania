package com.example.android.popularmovies.theMovieDbApi.responses;

import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.theMovieDbApi.ApiUtilities;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Entity Class for the movie db response
 */
public class MovieListResponse {

    @SerializedName(ApiUtilities.RESPONSE_PAGE)
    private int mPage;

    @SerializedName(ApiUtilities.RESPONSE_RESULTS)
    private List<Movie> mResults;

    @SerializedName(ApiUtilities.RESPONSE_TOTAL_RESULTS)
    private int mTotalResults;

    @SerializedName(ApiUtilities.RESPONSE_TOTAL_PAGES)
    private int mTotalPages;

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public List<Movie> getResults() {
        return mResults;
    }

    public void setResults(List<Movie> results) {
        mResults = results;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(int totalResults) {
        mTotalResults = totalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(int totalPages) {
        mTotalPages = totalPages;
    }
}
