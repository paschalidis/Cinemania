package com.example.android.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.models.Review;

import java.util.ArrayList;
import java.util.List;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.MovieReviewAdapterViewHolder> {

    private Context mContext;
    private List<Review> mReviewList;

    public MovieReviewAdapter(Context context) {
        mContext = context;
        mReviewList = new ArrayList<>();
    }

    public class MovieReviewAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mReviewAuthor;
        public final TextView mReviewContent;

        public MovieReviewAdapterViewHolder(View itemView) {
            super(itemView);
            mReviewAuthor = (TextView) itemView.findViewById(R.id.tv_review_author);
            mReviewContent = (TextView) itemView.findViewById(R.id.tv_review_content);
        }

        private void bind(String author, String content) {
            mReviewAuthor.setText(author);
            mReviewContent.setText(content);
        }
    }

    @Override
    public MovieReviewAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdMovieReviewItem = R.layout.movie_review_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdMovieReviewItem, viewGroup, shouldAttachToParentImmediately);

        return new MovieReviewAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieReviewAdapterViewHolder holder, int position) {
        holder.bind(mReviewList.get(position).getAuthor(),
                mReviewList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        if (mReviewList == null) {
            return 0;
        }

        return mReviewList.size();
    }

    /**
     * This method is used to set the movie list on a MovieReviewAdapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new MovieReviewAdapter to display it.
     *
     * @param reviewData The new review data to be displayed.
     */
    public void setReviewData(List<Review> reviewData) {
        mReviewList = reviewData;
        notifyDataSetChanged();
    }
}
