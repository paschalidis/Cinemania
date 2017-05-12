package com.example.android.popularmovies.theMovieDbApi;

import android.util.Log;

import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.models.Review;
import com.example.android.popularmovies.models.Trailer;
import com.example.android.popularmovies.theMovieDbApi.responses.MovieListResponse;
import com.example.android.popularmovies.theMovieDbApi.responses.ReviewListResponse;
import com.example.android.popularmovies.theMovieDbApi.responses.TrailerListResponse;
import com.example.android.popularmovies.utilities.NetworkUtilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Controller Class that make the api requests
 */
public class ApiController {

    private static final String TAG = ApiController.class.getSimpleName();

    /**
     * The main function to get movie data.
     * <p>
     * Get as parameter the api endpoint and the page, make the call to the
     * url and handle the movie data to ArrayList.
     *
     * @param apiEndpoint String The endpoint to request
     * @param page        Int The page to request
     * @return List<Movie> The list with movies
     */
    public List<Movie> getMovies(String apiEndpoint, int page) {
        List<Movie> movieList = new ArrayList<>();

        if (!NetworkUtilities.hasInternetConnection()) {
            return null;
        }

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieListResponse> call = apiInterface.getMovies(apiEndpoint,
                page, ApiUtilities.API_KEY);

        try {
            movieList = call.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Number of movies to return: " + movieList.size());
        return movieList;
    }

    /**
     * The main function to get movie trailer data.
     * <p>
     * Get as parameter the movie id and make the call to the
     * url and handle the movie trailer data to ArrayList.
     *
     * @param movieId String The movie to request
     * @return List<Trailer> The list with movies
     */
    public List<Trailer> getTrailers(String movieId) {
        List<Trailer> trailerList = new ArrayList<>();

        if (movieId == null) {
            return null;
        }

        if (!NetworkUtilities.hasInternetConnection()) {
            return null;
        }

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<TrailerListResponse> call = apiInterface.getMovieVideos(movieId,
                ApiUtilities.API_KEY);

        try {
            trailerList = call.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Number of trailers to return: " + trailerList.size());
        return trailerList;
    }

    /**
     * The main function to get movie reviews data.
     * <p>
     * Get as parameter the movie id and make the call to the
     * url and handle the movie reviews data to ArrayList.
     *
     * @param movieId String The movie to request
     * @return List<Review> The list with movies
     */
    public List<Review> getReviews(String movieId) {
        List<Review> reviewList = new ArrayList<>();

        if (movieId == null) {
            return null;
        }

        if (!NetworkUtilities.hasInternetConnection()) {
            return null;
        }

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ReviewListResponse> call = apiInterface.getMovieRevies(movieId,
                ApiUtilities.API_KEY);

        try {
            reviewList = call.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Number of reviews to return: " + reviewList.size());
        return reviewList;
    }
}
