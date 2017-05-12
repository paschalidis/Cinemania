package com.example.android.popularmovies.theMovieDbApi.responses;

import com.example.android.popularmovies.models.Trailer;
import com.example.android.popularmovies.theMovieDbApi.ApiUtilities;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerListResponse {

    @SerializedName(ApiUtilities.RESPONSE_ID)
    private String mId;

    @SerializedName(ApiUtilities.RESPONSE_RESULTS)
    private List<Trailer> mResults;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public List<Trailer> getResults() {
        return mResults;
    }

    public void setResults(List<Trailer> results) {
        mResults = results;
    }
}
