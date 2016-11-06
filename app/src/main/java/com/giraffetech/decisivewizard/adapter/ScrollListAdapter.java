package com.giraffetech.decisivewizard.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.listener.ScrollListItemHandler;
import com.giraffetech.decisivewizard.listitem.ScrollListItem;
import com.giraffetech.decisivewizard.viewholder.ScrollListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ScrollListAdapter extends RecyclerView.Adapter<ScrollListViewHolder> {

    //region Fields
    @NonNull
    private List<ScrollListItem> mScrollListItems = new ArrayList<>();

    @Nullable
    private ScrollListItemHandler mScrollListItemHandler;
    //endregion Fields

    //region Constructors
    public ScrollListAdapter() {
    }
    //endregion Constructors

    //region Getters and Setters
    public void setItems(List<ScrollListItem> items) {
        mScrollListItems = items;
    }

    public List<ScrollListItem> getItems() {
        return mScrollListItems;
    }

    public void addItem(ScrollListItem scrollListItem) {
        mScrollListItems.add(scrollListItem);
    }

    public void setScrollListItemHandler(ScrollListItemHandler scrollListItemHandler) {
        mScrollListItemHandler = scrollListItemHandler;
    }
    //endregion Getters and Setters

    //region RecyclerView.Adapter Methods
    @Override
    public ScrollListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scroll_list_item, parent, false);

        return new ScrollListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScrollListViewHolder viewHolder, int position) {
        ScrollListItem item = mScrollListItems.get(position);

        viewHolder.getBinding().setHandler(mScrollListItemHandler);
        viewHolder.getBinding().setScrollListItem(item);
        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mScrollListItems.size();
    }
    //endregion RecyclerView.Adapter Methods

}
