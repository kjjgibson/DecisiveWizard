package com.giraffetech.decisivewizard.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.model.Scroll;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Scroll} and makes a call to the
 * specified {@link ScrollListFragment.OnListFragmentInteractionListener}.
 */
public class ScrollRecyclerViewAdapter extends RecyclerView.Adapter<ScrollRecyclerViewAdapter.ViewHolder> {

    private final List<Scroll> mScrolls;
    private final ScrollListFragment.OnListFragmentInteractionListener mListener;

    public ScrollRecyclerViewAdapter(List<Scroll> scrolls, ScrollListFragment.OnListFragmentInteractionListener listener) {
        mScrolls = scrolls;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_scroll, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mScroll = mScrolls.get(position);
        holder.mNameTextView.setText(mScrolls.get(position).getName());
        holder.mDescriptionTextView.setText(mScrolls.get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mScroll);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mScrolls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameTextView;
        public final TextView mDescriptionTextView;
        public Scroll mScroll;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameTextView = (TextView) view.findViewById(R.id.textViewName);
            mDescriptionTextView = (TextView) view.findViewById(R.id.textViewDescription);
        }
    }
}
