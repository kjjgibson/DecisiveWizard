package com.giraffetech.decisivewizard.dependencyinjection.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.giraffetech.decisivewizard.adapter.ScrollCardAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ScrollCardsFragmentModule extends BaseContextModule {

    public ScrollCardsFragmentModule(Context context) {
        super(context);
    }

    @Singleton
    @Provides
    public ScrollCardAdapter provideScrollCardAdapter() {
        return new ScrollCardAdapter();
    }

    @Provides
    public RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

}
