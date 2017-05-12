package com.example.android.popularmovies;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.android.popularmovies.database.DbController;
import com.example.android.popularmovies.fragments.MovieListFragment;
import com.example.android.popularmovies.fragments.MovieReviewFragment;
import com.example.android.popularmovies.fragments.MovieTrailerFragment;
import com.example.android.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private Movie mMovieEntity;
    private ImageView mPoster;
    private ImageView mBackdrop;
    private TextView mReleaseDate;
    private TextView mVoteAverage;
    private TextView mOverview;
    private ToggleButton mFavoriteButton;
    private DbController mDbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle(R.string.detail_name);

        mPoster = (ImageView) findViewById(R.id.iv_detail_poster);
        mBackdrop = (ImageView) findViewById(R.id.iv_detail_backdrop);
        mReleaseDate = (TextView) findViewById(R.id.tv_detail_release_date);
        mVoteAverage = (TextView) findViewById(R.id.tv_detail_vote_average);
        mOverview = (TextView) findViewById(R.id.tv_detail_overview);
        mFavoriteButton = (ToggleButton) findViewById(R.id.tb_favorites);
        mDbController = new DbController(this);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(MovieListFragment.MOVIE_ENTITY)) {
                mMovieEntity = intentThatStartedThisActivity.getParcelableExtra(MovieListFragment.MOVIE_ENTITY);
                collapsingToolbarLayout.setTitle(mMovieEntity.getOriginalTitle());
                Picasso.with(this)
                        .load(mMovieEntity.getPosterEndpoint())
                        .placeholder(R.drawable.default_movie_poster)
                        .into(mPoster);

                Picasso.with(this)
                        .load(mMovieEntity.getBackdropEndpoint())
                        .placeholder(R.drawable.default_movie_backdrop)
                        .into(mBackdrop);

                mReleaseDate.setText(mMovieEntity.getReleaseDate());
                String voteAverageDisplay = mMovieEntity.getVoteAverage() + "/10";
                mVoteAverage.setText(voteAverageDisplay);
                mOverview.setText(mMovieEntity.getOverview());

                mFavoriteButton.setChecked(mDbController.existsOnFavorites(mMovieEntity.getMovieId()));

                MovieTrailerFragment movieTrailerFragment = (MovieTrailerFragment)
                        getSupportFragmentManager()
                                .findFragmentById(R.id.detail_movie_trailer_fragment);
                movieTrailerFragment.setMovieId(mMovieEntity.getMovieId());

                MovieReviewFragment movieReviewFragment = (MovieReviewFragment)
                        getSupportFragmentManager()
                                .findFragmentById(R.id.detail_movie_review_fragment);
                movieReviewFragment.setMovieId(mMovieEntity.getMovieId());
            }
        }

        mFavoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean hasUpdated;
                if (isChecked) {
                    hasUpdated = mDbController.addToFavorites(mMovieEntity);
                } else {
                    hasUpdated = mDbController.removeFromFavorites(mMovieEntity.getMovieId());
                }

                if (!hasUpdated) {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.error_database),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * This method is called when the Share Movie Content button is clicked.
     * It will simply share the movie public url.
     *
     * @param view Button that was clicked.
     */
    public void onClickShareMovieButton(View view) {

        String mimeType = "text/plain";
        String chooserTitle = "Share Movie";

        ShareCompat.IntentBuilder.from(this)
                .setType(mimeType)
                .setChooserTitle(chooserTitle)
                .setText(mMovieEntity.getMovieEndpoint())
                .startChooser();
    }
}
