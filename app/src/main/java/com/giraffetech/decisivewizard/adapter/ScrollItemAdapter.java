package com.giraffetech.decisivewizard.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.callback.DragItemTouchHelperCallback;
import com.giraffetech.decisivewizard.listener.OnStartDragListener;
import com.giraffetech.decisivewizard.listener.ScrollItemListItemHandler;
import com.giraffetech.decisivewizard.listitem.ScrollItemListItem;
import com.giraffetech.decisivewizard.textwatcher.SimpleTextWatcher;
import com.giraffetech.decisivewizard.viewholder.FooterViewHolder;
import com.giraffetech.decisivewizard.viewholder.ScrollItemViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScrollItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DragItemTouchHelperCallback.ItemTouchHelperAdapter, ScrollItemListItemHandler {

    //region Fields
    private static final int FOOTER_VIEW = 1;

    @NonNull
    private List<ScrollItemListItem> mScrollItemListItems = new ArrayList<>();

    @Nullable
    private OnStartDragListener mDragStartListener;
    //endregion Fields

    //region Constructors
    public ScrollItemAdapter() {
    }
    //endregion Constructors

    //region RecyclerView.Adapter Methods
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder;

        if (viewType == FOOTER_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_scroll_item_list_item, parent, false);

            viewHolder = new FooterViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scroll_item_list_item, parent, false);

            final ScrollItemViewHolder itemViewHolder = new ScrollItemViewHolder(view, new SimpleTextWatcher());
            itemViewHolder.getScrollItemEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        itemViewHolder.getRemoveImageButton().setVisibility(View.VISIBLE);
                    } else {
                        itemViewHolder.getRemoveImageButton().setVisibility(View.INVISIBLE);
                    }
                }
            });

            itemViewHolder.getDragHandleImageView().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        if (mDragStartListener != null) {
                            mDragStartListener.onStartDrag(itemViewHolder);
                        }
                    }
                    return false;
                }
            });

            viewHolder = itemViewHolder;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ScrollItemViewHolder) {
            ScrollItemViewHolder itemViewHolder = (ScrollItemViewHolder) viewHolder;

            final ScrollItemListItem item = mScrollItemListItems.get(position);

            //Items should have focus when they are first added to the adapter
            if (item.shouldHaveFocus()) {
                itemViewHolder.getScrollItemEditText().requestFocus();
                item.shouldHaveFocus(false);
            }

            itemViewHolder.getTextWatcher().setListener(new SimpleTextWatcher.SimpleTextWatcherListener() {
                @Override
                public void onEnterKeyPressed() {
                    addNewItemAfter(item);
                }
            });
            itemViewHolder.getBinding().setHandler(this);
            itemViewHolder.getBinding().setScrollItemListItem(item);
            itemViewHolder.getBinding().executePendingBindings();
        } else if (viewHolder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) viewHolder;

            footerViewHolder.getBinding().setHandler(this);
        }
    }

    @Override
    public int getItemCount() {
        //We add 1 to the items size to account for the footer view
        return mScrollItemListItems.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mScrollItemListItems.size()) {
            return FOOTER_VIEW;
        }

        return super.getItemViewType(position);
    }
    //endregion RecyclerView.Adapter Methods

    //region ItemTouchHelperAdapter Methods
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        boolean moved = false;

        //Don't allow dragging of the footer view
        if (getItemViewType(fromPosition) != FOOTER_VIEW) {
            if (toPosition < mScrollItemListItems.size()) {
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mScrollItemListItems, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mScrollItemListItems, i, i - 1);
                    }
                }
                notifyItemMoved(fromPosition, toPosition);
                moved = true;
            }
        }

        return moved;
    }
    //endregion ItemTouchHelperAdapter Methods

    //region ScrollItemListItemHandler Methods
    @Override
    public void removeItem(ScrollItemListItem itemToRemove) {
        int index = mScrollItemListItems.indexOf(itemToRemove);
        mScrollItemListItems.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public void addNewItemAfter(ScrollItemListItem item) {
        addNewItemAt(mScrollItemListItems.indexOf(item) + 1);
    }

    @Override
    public void addNewItemAtEnd() {
        addNewItemAt(mScrollItemListItems.size());
    }
    //endregion ScrollItemListItemHandler Methods

    //region Getters and Setters
    public void setItems(List<ScrollItemListItem> items) {
        mScrollItemListItems = items;
    }

    public void setOnStartDragListener(OnStartDragListener listener) {
        mDragStartListener = listener;
    }
    //endregion Getters and Setters

    //region Private Methods
    private void addNewItemAt(int position) {
        ScrollItemListItem newItem = new ScrollItemListItem("");
        newItem.shouldHaveFocus(true);

        mScrollItemListItems.add(position, newItem);
        notifyItemInserted(position);
    }
    //endregion Private Methods

}
