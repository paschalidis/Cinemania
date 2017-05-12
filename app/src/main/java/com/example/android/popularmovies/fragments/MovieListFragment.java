package com.example.android.popularmovies.fragments;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.android.popularmovies.DetailActivity;
import com.example.android.popularmovies.adapters.MovieAdapter;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.asyncTaskLoaders.MovieListLoader;
import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.theMovieDbApi.ApiUtilities;

import java.util.List;

/**
 * Fragment of Movie List
 */
public class MovieListFragment extends Fragment implements MovieAdapter.MovieAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<List<Movie>> {

    private static final String LOG_TAG = MovieListFragment.class.getSimpleName();

    private MovieAdapter mMovieAdapter;
    private RecyclerView mMovieRecyclerView;
    private ProgressBar mLoadingIndicator;
    private TextView mErrorMessageDisplay;
    private RelativeLayout mEmtyView;

    private String mSortSelected = ApiUtilities.DEFAULT_ENDPOINT;

    public final static String MOVIE_ENTITY = "movie_entity";
    private final static String BUNDLE_SORT_SELECTED = "sort_selected";
    protected final static int GRID_COLUMNS_PORTRAIT = 2;
    protected final static int GRID_COLUMNS_LANDSCAPE = 3;
    public final static int MOVIE_LOADER_ID = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        String favoritesSelected = getResources().getString(R.string.sort_favorites_movies);
        if(mSortSelected.equals(favoritesSelected)){
            getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_SORT_SELECTED, mSortSelected);
        Log.d(LOG_TAG, "On Save Instance State");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        mMovieRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_movies);

        mErrorMessageDisplay = (TextView) rootView.findViewById(R.id.tv_error_message_display);

        mEmtyView = (RelativeLayout) rootView.findViewById(R.id.empty_view);

        int orientation = getActivity().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mMovieRecyclerView.setLayoutManager(
                    new GridLayoutManager(getActivity(), GRID_COLUMNS_PORTRAIT));
        } else {
            mMovieRecyclerView.setLayoutManager(
                    new GridLayoutManager(getActivity(), GRID_COLUMNS_LANDSCAPE));
        }

        mMovieRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(getActivity(), this);
        mMovieRecyclerView.setAdapter(mMovieAdapter);

        mLoadingIndicator = (ProgressBar) rootView.findViewById(R.id.pb_loading_indicator);

        if(savedInstanceState != null){
            mSortSelected = savedInstanceState.getString(BUNDLE_SORT_SELECTED);
        }

        getLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);

        return rootView;
    }

    /**
     * This method will get the user's preferred movies sorted list, and then tell some
     * background method to get the movie data in the background.
     *
     * @param sortSelected The api endpoint for The Movie Db API
     */
    public void loadMovieData(String sortSelected) {
        mSortSelected = sortSelected;
        mMovieAdapter.clearData();
        getLoaderManager().restartLoader(MOVIE_LOADER_ID, null, this);
    }

    /**
     * This method will make the View for the movie data visible and
     * hide the error message.
     */
    private void showMovieDataView() {
        mEmtyView.setVisibility(View.INVISIBLE);
        mMovieRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the movie
     * View.
     */
    private void showErrorMessage() {
        mMovieRecyclerView.setVisibility(View.INVISIBLE);
        String favoritesSelected = getResources().getString(R.string.sort_favorites_movies);
        if(mSortSelected.equals(favoritesSelected)){
            mErrorMessageDisplay.setText(getResources().getString(R.string.error_empty_favorites));
        } else {
            mErrorMessageDisplay.setText(getResources().getString(R.string.error_no_internet_connection));
        }
        mEmtyView.setVisibility(View.VISIBLE);
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, final Bundle args) {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        return new MovieListLoader(getActivity(), mSortSelected);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader,
                               List<Movie> movieList) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        if (movieList != null && movieList.size() > 0) {
            mMovieAdapter.setMovieData(movieList);
            showMovieDataView();
        } else {
            showErrorMessage();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        mMovieAdapter.clearData();
    }

    /**
     * Create new Intent to details activity
     *
     * @param movie Movie to Pass in intent
     */
    @Override
    public void onClick(Movie movie) {
        Intent intentToStartDetailActivity = new Intent(getActivity(), DetailActivity.class);
        intentToStartDetailActivity.putExtra(MOVIE_ENTITY, movie);
        startActivity(intentToStartDetailActivity);
    }
}
