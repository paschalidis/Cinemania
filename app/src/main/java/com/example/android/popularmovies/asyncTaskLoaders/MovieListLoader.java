package com.example.android.popularmovies.asyncTaskLoaders;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.database.DbController;
import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.theMovieDbApi.ApiController;
import com.example.android.popularmovies.theMovieDbApi.ApiUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * AsyncTaskLoader for movie list
 */
public class MovieListLoader extends AsyncTaskLoader<List<Movie>> {

    private List<Movie> mMovieList;
    private String mSortSelected;
    private int mPageParam;
    private Context mContext;

    public MovieListLoader(Context context, String sortSelected) {
        super(context);
        mMovieList = new ArrayList<>();
        mSortSelected = sortSelected;
        mPageParam = ApiUtilities.DEFAULT_PAGE;
        mContext = context;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mMovieList == null || mMovieList.size() == 0) {
            forceLoad();
        } else {
            deliverResult(mMovieList);
        }
    }

    @Override
    public List<Movie> loadInBackground() {
        List<Movie> movieList;
        String favoritesString = mContext.getResources().getString(R.string.sort_favorites_movies);
        if (mSortSelected.equals(favoritesString)) {
            DbController dbController = new DbController(mContext);
            movieList = dbController.getFavorites();
        } else {
            ApiController apiController = new ApiController();
            movieList = apiController.getMovies(mSortSelected, mPageParam);
        }

        return movieList;
    }

    @Override
    public void deliverResult(List<Movie> movieList) {
        mMovieList = movieList;
        super.deliverResult(mMovieList);
    }
}

