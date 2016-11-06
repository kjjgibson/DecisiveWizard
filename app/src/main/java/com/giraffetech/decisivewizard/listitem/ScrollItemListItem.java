package com.giraffetech.decisivewizard.listitem;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.giraffetech.decisivewizard.BR;

public class ScrollItemListItem extends BaseObservable {

    //region Fields
    //The text that should be displayed in the EditText
    private String mItemText;

    //True if this EditText should have focus
    //Should be set to true when an item is newly added to the adapter
    private boolean mShouldHaveFocus = false;
    //endregion Fields

    //region Constructors
    public ScrollItemListItem() {

    }

    public ScrollItemListItem(@NonNull String itemText) {
        mItemText = itemText;
    }
    //endregion Constructors

    //region Getters and Setters
    @Bindable
    public String getItemText() {
        return mItemText;
    }

    public void setItemText(String itemText) {
        mItemText = itemText;
        notifyPropertyChanged(BR.itemText);
    }

    public boolean shouldHaveFocus() {
        return mShouldHaveFocus;
    }

    public void shouldHaveFocus(boolean shouldHaveFocus) {
        mShouldHaveFocus = shouldHaveFocus;
    }
    //endregion Getters and Setters

}
