package com.example.android.popularmovies.theMovieDbApi;

import com.example.android.popularmovies.BuildConfig;

/**
 * ApiUtilities Class store all hardcoded string
 * used for The Movie Database API.
 *
 * @author Pavlos Paschalidis
 * @see <a href="https://developers.themoviedb.org/3">API Documentation</a>
 */
public final class ApiUtilities {

    /**
     * General
     */
    public static final String API_BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static final String API_IMAGE_URL = "https://image.tmdb.org/t/p";
    public static final String MOVIE_PUBLIC_URL = "https://www.themoviedb.org/movie/";
    public static final String API_KEY = BuildConfig.THE_MOVIE_DB_API_TOKEN;
    public static final String IMAGE_POSTER_SIZE = "/w300";
    public static final String IMAGE_BACKDROP_SIZE = "/w500";

    /**
     * Url Parameters
     */
    public static final String PARAM_PAGE = "page";
    public static final String PARAM_API_KEY = "api_key";
    public static final String PARAM_GET_SORT_PATH = "sort";
    public static final String PARAM_GET_ID_PATH = "id";
    /**
     * Endpoints
     */
    public static final String ENDPOINT_POPULAR = "popular";
    public static final String ENDPOINT_TOP_RATED = "top_rated";
    public static final String ENDPOINT_GET_SORT = "{" + PARAM_GET_SORT_PATH + "}";
    public static final String ENDPOINT_GET_VIDEOS = "{" + PARAM_GET_ID_PATH + "}/videos";
    public static final String ENDPOINT_GET_REVIEWS = "{" + PARAM_GET_ID_PATH + "}/reviews";

    /**
     * Default Api Preferences
     */
    public static final int DEFAULT_PAGE = 1;
    public static final String DEFAULT_ENDPOINT = ENDPOINT_POPULAR;

    /**
     * Api Response
     */
    public static final String RESPONSE_RESULTS = "results";
    public static final String RESPONSE_PAGE = "page";
    public static final String RESPONSE_TOTAL_RESULTS = "total_results";
    public static final String RESPONSE_TOTAL_PAGES = "total_pages";
    public static final String RESPONSE_ID = "id";

    /**
     * Movie Entity
     */
    public static final String MOVIE_POSTER_PATH = "poster_path";
    public static final String MOVIE_OVERVIEW = "overview";
    public static final String MOVIE_RELEASE_DATE = "release_date";
    public static final String MOVIE_ID = "id";
    public static final String MOVIE_ORIGINAL_TITLE = "original_title";
    public static final String MOVIE_VOTE_AVERAGE = "vote_average";
    public static final String MOVIE_BACKDROP_PATH = "backdrop_path";

    /**
     * Trailer Entity
     */
    public static final String TRAILER_ID = "id";
    public static final String TRAILER_NAME = "name";
    public static final String TRAILER_KEY = "key";

    /**
     * Review Entity
     */
    public static final String REVIEW_ID = "id";
    public static final String REVIEW_AUTHOR = "author";
    public static final String REVIEW_CONTENT = "content";
    public static final String REVIEW_URL = "url";

    /**
     * Youtube End Points
     */
    public final static String YOUTUBE_IMAGE_ENDPOINT = "http://img.youtube.com/vi/";
    public final static String YOUTUBE_IMAGE_JPG = "/sddefault.jpg";
    public final static String YOUTUBE_VIDEO_ENDPOINT = "https://www.youtube.com/watch?v=";
}
