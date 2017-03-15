package com.giraffetech.decisivewizard.dependencyinjection.module;

import android.content.Context;

public class BaseContextModule {

    protected final Context mContext;

    public BaseContextModule(Context context) {
        mContext = context;
    }

}
