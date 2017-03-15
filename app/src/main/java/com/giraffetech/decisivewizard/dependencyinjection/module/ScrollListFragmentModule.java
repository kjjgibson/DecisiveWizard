package com.giraffetech.decisivewizard.dependencyinjection.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.giraffetech.decisivewizard.adapter.ScrollListAdapter;
import com.giraffetech.decisivewizard.itemdecoration.DividerItemDecoration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ScrollListFragmentModule extends BaseContextModule {

    public ScrollListFragmentModule(Context context) {
        super(context);
    }

    @Singleton
    @Provides
    public ScrollListAdapter provideScrollListAdapter() {
        return new ScrollListAdapter();
    }

    @Provides
    public RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Provides
    public DividerItemDecoration provideDividerItemDecoration() {
        return new DividerItemDecoration(mContext);
    }

}
