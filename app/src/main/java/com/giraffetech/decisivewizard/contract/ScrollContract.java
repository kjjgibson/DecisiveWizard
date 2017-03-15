package com.giraffetech.decisivewizard.contract;

import android.provider.BaseColumns;

public final class ScrollContract {

    private ScrollContract() {
    }

    public static class Scroll implements BaseColumns {
        public static final String TABLE_NAME = "scrolls";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_SCROLL_ITEMS = "scroll_items";
    }
    
}
