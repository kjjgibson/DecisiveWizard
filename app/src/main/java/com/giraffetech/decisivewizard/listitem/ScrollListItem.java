package com.giraffetech.decisivewizard.listitem;

import android.support.annotation.NonNull;

import com.giraffetech.decisivewizard.model.Scroll;

public class ScrollListItem {

    //region Fields
    private Scroll mScroll;
    //endregion Fields

    //region Constructors
    public ScrollListItem(@NonNull Scroll scroll) {
        mScroll = scroll;
    }
    //endregion Constructors

    //region Getters and Setters
    public Scroll getScroll() {
        return mScroll;
    }

    public String getName() {
        return mScroll != null ? mScroll.getName() : "";
    }

    public String getDescription() {
        return mScroll != null ? mScroll.getDescription() : "";
    }
    //endregion Getters and Setters

}
