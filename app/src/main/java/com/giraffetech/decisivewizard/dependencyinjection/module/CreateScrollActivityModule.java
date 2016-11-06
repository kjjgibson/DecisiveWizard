package com.giraffetech.decisivewizard.dependencyinjection.module;

import com.giraffetech.decisivewizard.fragment.CreateScrollFragment;
import com.giraffetech.decisivewizard.model.Scroll;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateScrollActivityModule {

    private Scroll mScroll;

    public CreateScrollActivityModule(Scroll scroll) {
        mScroll = scroll;
    }

    @Provides
    public CreateScrollFragment provideCreateScrollFragment(Scroll scroll) {
        return CreateScrollFragment.newInstance(scroll);
    }

    @Provides
    public Scroll provideScroll() {
        return mScroll;
    }

}
