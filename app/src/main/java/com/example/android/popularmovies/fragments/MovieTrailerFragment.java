package com.example.android.popularmovies.fragments;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapters.MovieTrailerAdapter;
import com.example.android.popularmovies.asyncTaskLoaders.TrailerListLoader;
import com.example.android.popularmovies.models.Trailer;
import com.example.android.popularmovies.theMovieDbApi.ApiUtilities;

import java.util.List;


public class MovieTrailerFragment extends Fragment implements
        MovieTrailerAdapter.MovieTrailerAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<List<Trailer>> {

    private MovieTrailerAdapter mMovieTrailerAdapter;
    private RecyclerView mTrailerRecyclerView;
    private String mMovieId;

    private final static int TRAILER_LOADER_ID = 1;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_trailer, container, false);

        mTrailerRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_movie_trailer);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()
                , LinearLayoutManager.HORIZONTAL, false);
        mTrailerRecyclerView.setLayoutManager(linearLayoutManager);

        mMovieTrailerAdapter = new MovieTrailerAdapter(getActivity(), this);
        mTrailerRecyclerView.setAdapter(mMovieTrailerAdapter);

        getLoaderManager().initLoader(TRAILER_LOADER_ID, null, this);
        return rootView;
    }

    @Override
    public void onClick(Trailer trailer) {
        Uri youTubeUri = Uri.parse(ApiUtilities.YOUTUBE_VIDEO_ENDPOINT + trailer.getKey());
        Intent intentToOpenYoutube = new Intent(Intent.ACTION_VIEW, youTubeUri);
        if (intentToOpenYoutube.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intentToOpenYoutube);
        }
    }

    @Override
    public Loader<List<Trailer>> onCreateLoader(int id, Bundle args) {
        return new TrailerListLoader(getActivity(), mMovieId);
    }

    @Override
    public void onLoadFinished(Loader<List<Trailer>> loader, List<Trailer> trailerList) {
        if (trailerList != null && trailerList.size() > 0) {
            mMovieTrailerAdapter.setTrailerData(trailerList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Trailer>> loader) {

    }

    public void setMovieId(String movieId) {
        mMovieId = movieId;
        getLoaderManager().restartLoader(TRAILER_LOADER_ID, null, this);
    }
}
