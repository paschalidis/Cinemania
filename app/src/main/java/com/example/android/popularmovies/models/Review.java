package com.example.android.popularmovies.models;

import com.example.android.popularmovies.theMovieDbApi.ApiUtilities;
import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName(ApiUtilities.REVIEW_ID)
    private String mId;

    @SerializedName(ApiUtilities.REVIEW_AUTHOR)
    private String mAuthor;

    @SerializedName(ApiUtilities.REVIEW_CONTENT)
    private String mContent;

    @SerializedName(ApiUtilities.REVIEW_URL)
    private String mUrl;

    public Review(String id, String author, String content, String url) {
        mId = id;
        mAuthor = author;
        mContent = content;
        mUrl = url;
    }

    public String getId() {
        return mId;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getContent() {
        return mContent;
    }

    public String getUrl() {
        return mUrl;
    }
}
