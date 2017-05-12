package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.popularmovies.fragments.MovieListFragment;
import com.example.android.popularmovies.theMovieDbApi.ApiUtilities;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String BUNDLE_TITLE_KEY = "title";
    private final static String BUNDLE_SORT_SELECTED = "sort_selected";

    private int mSortSelectedId = R.id.sort_popular_movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.label_popular_movies);

        if (savedInstanceState != null) {
            String title = savedInstanceState.getString(BUNDLE_TITLE_KEY);
            Log.d(LOG_TAG, "Title on saveInstanceState: " + title);
            setTitle(title);
            mSortSelectedId = savedInstanceState.getInt(BUNDLE_SORT_SELECTED);
        }
        Log.d(LOG_TAG, "Main On Create");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_TITLE_KEY, getTitle().toString());
        outState.putInt(BUNDLE_SORT_SELECTED, mSortSelectedId);
        Log.d(LOG_TAG, "Main On Save Instance State");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        //Check If Has Selected New One
        if (itemId == mSortSelectedId) {
            return super.onOptionsItemSelected(item);
        }

        mSortSelectedId = itemId;

        MovieListFragment movieListFragment = (MovieListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_activity_fragment);
        // User clicked on a menu option in the app bar overflow menu
        switch (itemId) {
            case R.id.sort_popular_movies:
                movieListFragment.loadMovieData(ApiUtilities.ENDPOINT_POPULAR);
                setTitle(R.string.label_popular_movies);
                return true;
            case R.id.sort_top_rated_movies:
                movieListFragment.loadMovieData(ApiUtilities.ENDPOINT_TOP_RATED);
                setTitle(R.string.label_top_rated_movies);
                return true;
            case R.id.sort_favorites_movies:
                movieListFragment.loadMovieData(getString(R.string.sort_favorites_movies));
                setTitle(R.string.label_favorites_movies);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
