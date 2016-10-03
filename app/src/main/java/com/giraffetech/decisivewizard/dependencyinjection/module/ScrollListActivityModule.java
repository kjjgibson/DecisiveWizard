package com.giraffetech.decisivewizard.dependencyinjection.module;

import com.giraffetech.decisivewizard.fragment.ScrollCardsFragment;
import com.giraffetech.decisivewizard.fragment.ScrollListFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ScrollListActivityModule {

    @Provides
    public ScrollListFragment provideScrollListFragment() {
        return ScrollListFragment.newInstance();
    }

    @Provides
    public ScrollCardsFragment provideScrollCardsFragment() {
        return ScrollCardsFragment.newInstance();
    }

}
