package com.example.android.popularmovies.theMovieDbApi;

import com.example.android.popularmovies.theMovieDbApi.responses.MovieListResponse;
import com.example.android.popularmovies.theMovieDbApi.responses.ReviewListResponse;
import com.example.android.popularmovies.theMovieDbApi.responses.TrailerListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(ApiUtilities.ENDPOINT_GET_SORT)
    Call<MovieListResponse> getMovies(@Path(ApiUtilities.PARAM_GET_SORT_PATH) String endpoint,
                                      @Query(ApiUtilities.PARAM_PAGE) int page,
                                      @Query(ApiUtilities.PARAM_API_KEY) String apiKey);

    @GET(ApiUtilities.ENDPOINT_GET_VIDEOS)
    Call<TrailerListResponse> getMovieVideos(@Path(ApiUtilities.PARAM_GET_ID_PATH) String id,
                                             @Query(ApiUtilities.PARAM_API_KEY) String apiKey);

    @GET(ApiUtilities.ENDPOINT_GET_REVIEWS)
    Call<ReviewListResponse> getMovieRevies(@Path(ApiUtilities.PARAM_GET_ID_PATH) String id,
                                            @Query(ApiUtilities.PARAM_API_KEY) String apiKey);
}
