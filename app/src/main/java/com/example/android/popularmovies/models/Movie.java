package com.example.android.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.popularmovies.theMovieDbApi.ApiUtilities;
import com.google.gson.annotations.SerializedName;

/**
 * Entity Class For Movie
 */
public class Movie implements Parcelable {

    @SerializedName(ApiUtilities.MOVIE_POSTER_PATH)
    private String mPosterPath;

    @SerializedName(ApiUtilities.MOVIE_ID)
    private String mMovieId;

    @SerializedName(ApiUtilities.MOVIE_RELEASE_DATE)
    private String mReleaseDate;

    @SerializedName(ApiUtilities.MOVIE_ORIGINAL_TITLE)
    private String mOriginalTitle;

    @SerializedName(ApiUtilities.MOVIE_OVERVIEW)
    private String mOverview;

    @SerializedName(ApiUtilities.MOVIE_VOTE_AVERAGE)
    private String mVoteAverage;

    @SerializedName(ApiUtilities.MOVIE_BACKDROP_PATH)
    private String mBackdropPath;

    public Movie(String mPosterPath, String mMovieId, String mReleaseDate,
                 String mOriginalTitle, String mOverview, String mVoteAverage,
                 String mBackdropPath) {
        this.mPosterPath = mPosterPath;
        this.mMovieId = mMovieId;
        this.mReleaseDate = mReleaseDate;
        this.mOriginalTitle = mOriginalTitle;
        this.mOverview = mOverview;
        this.mVoteAverage = mVoteAverage;
        this.mBackdropPath = mBackdropPath;
    }

    private Movie(Parcel in) {
        mPosterPath = in.readString();
        mMovieId = in.readString();
        mReleaseDate = in.readString();
        mOriginalTitle = in.readString();
        mOverview = in.readString();
        mVoteAverage = in.readString();
        mBackdropPath = in.readString();
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getMovieId() {
        return mMovieId;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getPosterEndpoint() {
        String posterEndpoint = ApiUtilities.API_IMAGE_URL +
                ApiUtilities.IMAGE_POSTER_SIZE +
                mPosterPath;
        return posterEndpoint;
    }

    public String getMovieEndpoint() {
        String movieEndpoint = ApiUtilities.MOVIE_PUBLIC_URL + mMovieId;
        return movieEndpoint;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public String getBackdropEndpoint() {

        String imagePath;

        //Show poster path alternative
        if (null == mBackdropPath || mBackdropPath.isEmpty()) {
            imagePath = mPosterPath;
        } else {
            imagePath = mBackdropPath;
        }

        String posterEndpoint = ApiUtilities.API_IMAGE_URL +
                ApiUtilities.IMAGE_BACKDROP_SIZE +
                imagePath;

        return posterEndpoint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPosterPath);
        dest.writeString(mMovieId);
        dest.writeString(mReleaseDate);
        dest.writeString(mOriginalTitle);
        dest.writeString(mOverview);
        dest.writeString(mVoteAverage);
        dest.writeString(mBackdropPath);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }

    };
}
