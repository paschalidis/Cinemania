package com.example.android.popularmovies.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapters.MovieReviewAdapter;
import com.example.android.popularmovies.asyncTaskLoaders.ReviewListLoader;
import com.example.android.popularmovies.models.Review;

import java.util.List;

public class MovieReviewFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Review>> {

    private MovieReviewAdapter mMovieReviewAdapter;
    private RecyclerView mReviewRecyclerView;
    private CardView mCardView;
    private String mMovieId;

    private final static int REVIEW_LOADER_ID = 1;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_review, container, false);

        mCardView = (CardView) rootView.findViewById(R.id.movie_review_content);

        mReviewRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_movie_review);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()
                , LinearLayoutManager.VERTICAL, false);
        mReviewRecyclerView.setLayoutManager(linearLayoutManager);

        mMovieReviewAdapter = new MovieReviewAdapter(getActivity());
        mReviewRecyclerView.setAdapter(mMovieReviewAdapter);

        getLoaderManager().initLoader(REVIEW_LOADER_ID, null, this);

        return rootView;
    }

    @Override
    public Loader<List<Review>> onCreateLoader(int id, Bundle args) {
        return new ReviewListLoader(getActivity(), mMovieId);
    }

    @Override
    public void onLoadFinished(Loader<List<Review>> loader, List<Review> reviewList) {
        if (reviewList != null && reviewList.size() > 0) {
            mMovieReviewAdapter.setReviewData(reviewList);
        } else {
            mCardView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Review>> loader) {

    }

    public void setMovieId(String movieId) {
        mMovieId = movieId;
        getLoaderManager().restartLoader(REVIEW_LOADER_ID, null, this);
    }
}
