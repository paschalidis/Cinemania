package com.example.android.popularmovies.asyncTaskLoaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.popularmovies.models.Trailer;
import com.example.android.popularmovies.theMovieDbApi.ApiController;

import java.util.List;

public class TrailerListLoader extends AsyncTaskLoader<List<Trailer>> {

    private List<Trailer> mTrailerList;
    private String mMovieId;

    public TrailerListLoader(Context context, String movieId) {
        super(context);
        mMovieId = movieId;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mTrailerList == null || mTrailerList.size() == 0) {
            forceLoad();
        } else {
            deliverResult(mTrailerList);
        }
    }

    @Override
    public List<Trailer> loadInBackground() {
        ApiController apiController = new ApiController();
        return apiController.getTrailers(mMovieId);
    }

    @Override
    public void deliverResult(List<Trailer> trailerList) {
        mTrailerList = trailerList;
        super.deliverResult(mTrailerList);
    }
}
