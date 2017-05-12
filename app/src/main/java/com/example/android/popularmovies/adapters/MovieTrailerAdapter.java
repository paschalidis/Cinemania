package com.example.android.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.models.Trailer;
import com.example.android.popularmovies.theMovieDbApi.ApiUtilities;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * MovieTrailerAdapter Recycler View
 * <p>
 * {@link android.support.v7.widget.RecyclerView}
 */
public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.MovieTrailerAdapterViewHolder> {

    private Context mContext;
    private List<Trailer> mTrailerList;
    private final MovieTrailerAdapterOnClickHandler mClickHandler;

    public interface MovieTrailerAdapterOnClickHandler {
        void onClick(Trailer trailer);
    }

    public MovieTrailerAdapter(Context context, MovieTrailerAdapterOnClickHandler clickHandler) {
        mContext = context;
        mTrailerList = new ArrayList<>();
        mClickHandler = clickHandler;
    }


    public class MovieTrailerAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView mTrailerImageView;

        public MovieTrailerAdapterViewHolder(View itemView) {
            super(itemView);
            mTrailerImageView = (ImageView) itemView.findViewById(R.id.iv_movie_trailer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(mTrailerList.get(adapterPosition));
        }

        /**
         * A method we wrote for convenience. This method will take an string as input and
         * use that string to display the appropriate image within a list item.
         *
         * @param thumbnailEndpoint Sting
         */
        private void bind(String thumbnailEndpoint) {

            Picasso.with(mContext)
                    .load(thumbnailEndpoint)
                    .placeholder(R.drawable.deafult_youtube)
                    .error(R.drawable.deafult_youtube)
                    .into(mTrailerImageView);
        }
    }

    @Override
    public MovieTrailerAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdMovieTrailerItem = R.layout.movie_trailer_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdMovieTrailerItem, viewGroup, shouldAttachToParentImmediately);

        return new MovieTrailerAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieTrailerAdapterViewHolder holder, int position) {
        holder.bind(mTrailerList.get(position).getThumbnailEndpoint());
    }

    @Override
    public int getItemCount() {
        if (mTrailerList == null) {
            return 0;
        }

        return mTrailerList.size();
    }

    /**
     * This method is used to set the movie list on a MovieTrailerAdapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new MovieTrailerAdapter to display it.
     *
     * @param trailerData The new trailer data to be displayed.
     */
    public void setTrailerData(List<Trailer> trailerData) {
        mTrailerList = trailerData;
        notifyDataSetChanged();
    }
}
