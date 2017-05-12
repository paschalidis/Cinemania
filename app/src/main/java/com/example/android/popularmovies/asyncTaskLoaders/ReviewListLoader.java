package com.example.android.popularmovies.asyncTaskLoaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.popularmovies.models.Review;
import com.example.android.popularmovies.theMovieDbApi.ApiController;

import java.util.List;

public class ReviewListLoader extends AsyncTaskLoader<List<Review>> {

    private List<Review> mReviewList;
    private String mMovieId;

    public ReviewListLoader(Context context, String movieId) {
        super(context);
        mMovieId = movieId;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mReviewList == null || mReviewList.size() == 0) {
            forceLoad();
        } else {
            deliverResult(mReviewList);
        }
    }

    @Override
    public List<Review> loadInBackground() {
        ApiController apiController = new ApiController();
        return apiController.getReviews(mMovieId);
    }

    @Override
    public void deliverResult(List<Review> reviewList) {
        mReviewList = reviewList;
        super.deliverResult(mReviewList);
    }
}
