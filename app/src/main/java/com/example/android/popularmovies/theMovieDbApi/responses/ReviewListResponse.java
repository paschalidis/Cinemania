package com.example.android.popularmovies.theMovieDbApi.responses;

import com.example.android.popularmovies.models.Review;
import com.example.android.popularmovies.theMovieDbApi.ApiUtilities;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewListResponse {

    @SerializedName(ApiUtilities.RESPONSE_ID)
    private String mId;

    @SerializedName(ApiUtilities.RESPONSE_PAGE)
    private int mPage;

    @SerializedName(ApiUtilities.RESPONSE_RESULTS)
    private List<Review> mResults;

    @SerializedName(ApiUtilities.RESPONSE_TOTAL_RESULTS)
    private int mTotalResults;

    @SerializedName(ApiUtilities.RESPONSE_TOTAL_PAGES)
    private int mTotalPages;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public List<Review> getResults() {
        return mResults;
    }

    public void setResults(List<Review> results) {
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
