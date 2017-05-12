package com.example.android.popularmovies.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.android.popularmovies.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class DbController {

    private static final String TAG = DbController.class.getSimpleName();

    private Context mContext;

    public DbController(Context context) {
        mContext = context;
    }

    /**
     * The main function to get movie favorites data.
     *
     * @return List<Movie> The list with movies
     */
    public List<Movie> getFavorites() {
        List<Movie> movieList = new ArrayList<>();

        Cursor cursor;

        try {
            cursor = mContext.getContentResolver().query(
                    CinemaniaContract.MovieEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    null);
        } catch (Exception e) {
            Log.e(TAG, "Failed to asynchronously load data. Error:" + e);
            e.printStackTrace();
            return null;
        }

        if (null == cursor) {
            return null;
        }

        while (cursor.moveToNext()) {
            Movie movie = new Movie(
                    cursor.getString(
                            cursor.getColumnIndex(CinemaniaContract.MovieEntry.COLUMN_POSTER_PATH)),
                    cursor.getString(
                            cursor.getColumnIndex(CinemaniaContract.MovieEntry._ID)),
                    cursor.getString(
                            cursor.getColumnIndex(CinemaniaContract.MovieEntry.COLUMN_RELEASE_DATE)),
                    cursor.getString(
                            cursor.getColumnIndex(CinemaniaContract.MovieEntry.COLUMN_ORIGINAL_TITLE)),
                    cursor.getString(
                            cursor.getColumnIndex(CinemaniaContract.MovieEntry.COLUMN_OVERVIEW)),
                    cursor.getString(
                            cursor.getColumnIndex(CinemaniaContract.MovieEntry.COLUMN_VOTE_AVERAGE)),
                    cursor.getString(
                            cursor.getColumnIndex(CinemaniaContract.MovieEntry.COLUMN_BACKDROP_PATH))
            );
            movieList.add(movie);
        }

        cursor.close();

        return movieList;
    }

    /**
     * This function add movie to favorite list
     *
     * @param movie Movie
     * @return true if has added
     */
    public boolean addToFavorites(Movie movie) {
        boolean hasAdded = false;

        ContentValues contentValues = new ContentValues();

        contentValues.put(CinemaniaContract.MovieEntry._ID, movie.getMovieId());
        contentValues.put(CinemaniaContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        contentValues.put(CinemaniaContract.MovieEntry.COLUMN_ORIGINAL_TITLE, movie.getOriginalTitle());
        contentValues.put(CinemaniaContract.MovieEntry.COLUMN_OVERVIEW, movie.getOverview());
        contentValues.put(CinemaniaContract.MovieEntry.COLUMN_POSTER_PATH, movie.getPosterPath());
        contentValues.put(CinemaniaContract.MovieEntry.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        contentValues.put(CinemaniaContract.MovieEntry.COLUMN_BACKDROP_PATH, movie.getBackdropPath());

        Uri uri;
        try {
            uri = mContext.getContentResolver()
                    .insert(CinemaniaContract.MovieEntry.CONTENT_URI, contentValues);
        } catch (UnsupportedOperationException e) {
            Log.e(TAG, "Failed to Insert data. Error:" + e);
            e.printStackTrace();
            return false;
        }

        if (null != uri) {
            hasAdded = true;
        }

        return hasAdded;
    }

    /**
     * This function delete the specific movie id from the favorites list
     *
     * @param movieId String
     * @return true if has deleted
     */
    public boolean removeFromFavorites(String movieId) {
        int movieDeleted;
        boolean hasDeleted = false;

        Uri uri = CinemaniaContract.MovieEntry.CONTENT_URI;
        uri = uri.buildUpon().appendPath(movieId).build();

        try {
            movieDeleted = mContext.getContentResolver().delete(uri, null, null);
        } catch (UnsupportedOperationException e) {
            Log.e(TAG, "Failed to Delete data. Error:" + e);
            e.printStackTrace();
            return false;
        }

        if (movieDeleted != 0) {
            hasDeleted = true;
        }

        return hasDeleted;
    }

    /**
     * Check whatever a movie exists on favorite list
     *
     * @param movieId String
     * @return true if movie exists
     */
    public boolean existsOnFavorites(String movieId) {
        boolean isOnFavorites = false;

        Uri uri = CinemaniaContract.MovieEntry.CONTENT_URI;
        uri = uri.buildUpon().appendPath(movieId).build();

        Cursor cursor;
        try {
            cursor = mContext.getContentResolver().query(
                    uri,
                    null,
                    null,
                    null,
                    null);
        } catch (UnsupportedOperationException e) {
            Log.e(TAG, "Failed to asynchronously load data. Error:" + e);
            e.printStackTrace();
            return false;
        }

        if (null == cursor) {
            return false;
        }

        if (cursor.moveToFirst()) {
            isOnFavorites = true;
        }

        return isOnFavorites;
    }
}
