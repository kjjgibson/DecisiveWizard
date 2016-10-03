package com.giraffetech.decisivewizard.dependencyinjection.module;

import com.giraffetech.decisivewizard.fragment.CreateScrollFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class CreateScrollActivityModule {

    @Provides
    public CreateScrollFragment provideCreateScrollFragment() {
        return CreateScrollFragment.newInstance();
    }

}
