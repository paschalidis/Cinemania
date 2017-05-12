package com.example.android.popularmovies.models;

import com.example.android.popularmovies.theMovieDbApi.ApiUtilities;
import com.google.gson.annotations.SerializedName;

/**
 * Trailer Entity
 */
public class Trailer {

    @SerializedName(ApiUtilities.TRAILER_ID)
    private String mId;

    @SerializedName(ApiUtilities.TRAILER_NAME)
    private String mName;

    @SerializedName(ApiUtilities.TRAILER_KEY)
    private String mKey;

    public Trailer(String id, String name, String key) {
        mId = id;
        mName = name;
        mKey = key;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getKey() {
        return mKey;
    }

    public String getThumbnailEndpoint() {
        String thumbnailEndpoint = ApiUtilities.YOUTUBE_IMAGE_ENDPOINT
                + mKey
                + ApiUtilities.YOUTUBE_IMAGE_JPG;

        return thumbnailEndpoint;
    }
}
