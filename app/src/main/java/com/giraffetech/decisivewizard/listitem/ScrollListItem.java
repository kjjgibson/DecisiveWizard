package com.giraffetech.decisivewizard.listitem;

import com.giraffetech.decisivewizard.model.Scroll;

public class ScrollListItem {

    //region Fields
    private Scroll mScroll;
    //endregion Fields

    //region Constructors
    public ScrollListItem(Scroll scroll) {
        mScroll = scroll;
    }
    //endregion Constructors

    //region Getters and Setters
    public Scroll getScroll() {
        return mScroll;
    }

    public String getName() {
        return mScroll.getName();
    }

    public String getDescription() {
        return mScroll.getDescription();
    }
    //endregion Getters and Setters

}
