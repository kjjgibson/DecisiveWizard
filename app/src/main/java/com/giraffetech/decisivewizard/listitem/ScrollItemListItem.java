package com.giraffetech.decisivewizard.listitem;

import android.support.annotation.NonNull;

import com.giraffetech.decisivewizard.listener.ScrollItemListItemHandler;

public class ScrollItemListItem {

    //region Fields
    //Handler used to communicate back to the class containing the RecyclerView
    private ScrollItemListItemHandler mHandler;

    //The text that should be displayed in the EditText
    private String mItemText;

    //True if this EditText should have focus
    //Should be set to true when an item is newly added to the adapter
    private boolean mShouldHaveFocus = false;
    //endregion Fields

    //region Constructors
    public ScrollItemListItem() {

    }

    public ScrollItemListItem(@NonNull String itemText, ScrollItemListItemHandler handler) {
        mItemText = itemText;
        mHandler = handler;
    }
    //endregion Constructors

    //region Getters and Setters
    public String getItemText() {
        return mItemText;
    }

    public boolean shouldHaveFocus() {
        return mShouldHaveFocus;
    }

    public void shouldHaveFocus(boolean shouldHaveFocus) {
        mShouldHaveFocus = shouldHaveFocus;
    }

    public ScrollItemListItemHandler getHandler() {
        return mHandler;
    }
    //endregion Getters and Setters

    //region Public Methods
    public void setShouldHaveFocus(boolean shouldHaveFocus) {
        mShouldHaveFocus = shouldHaveFocus;
    }
    //endregion Public Methods

}
