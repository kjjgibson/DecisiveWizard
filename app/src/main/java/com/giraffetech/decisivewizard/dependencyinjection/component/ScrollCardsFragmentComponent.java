package com.giraffetech.decisivewizard.dependencyinjection.component;

import com.giraffetech.decisivewizard.fragment.ScrollCardsFragment;
import com.giraffetech.decisivewizard.dependencyinjection.module.ScrollCardsFragmentModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ScrollCardsFragmentModule.class})
public interface ScrollCardsFragmentComponent {

    void inject(ScrollCardsFragment scrollCardsFragment);

}
