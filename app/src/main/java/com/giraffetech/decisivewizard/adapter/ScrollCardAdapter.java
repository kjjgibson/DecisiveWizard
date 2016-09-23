package com.giraffetech.decisivewizard.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.listener.ScrollListItemHandler;
import com.giraffetech.decisivewizard.listitem.ScrollCardItem;
import com.giraffetech.decisivewizard.viewholder.ScrollCardViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ScrollCardAdapter extends RecyclerView.Adapter<ScrollCardViewHolder> {

    //region Fields
    private List<ScrollCardItem> mScrollCardItems = new ArrayList<>();
    private ScrollListItemHandler mScrollListItemHandler;
    //endregion Fields

    //region Constructors
    public ScrollCardAdapter(ScrollListItemHandler scrollListItemHandler) {
        mScrollListItemHandler = scrollListItemHandler;
    }
    //endregion Constructors

    //region Getters and Setters
    public List<ScrollCardItem> getItems() {
        return mScrollCardItems;
    }

    public void setItems(List<ScrollCardItem> items) {
        mScrollCardItems = items;
    }
    //endregion Getters and Setters

    //region RecyclerView.Adapter Methods
    @Override
    public ScrollCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scroll_card_item, parent, false);

        return new ScrollCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScrollCardViewHolder viewHolder, int position) {
        ScrollCardItem item = mScrollCardItems.get(position);

        viewHolder.getBinding().setHandler(mScrollListItemHandler);
        viewHolder.getBinding().setScrollCardItem(item);
        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mScrollCardItems.size();
    }
    //endregion RecyclerView.Adapter Methods

}
