package com.giraffetech.decisivewizard.dependencyinjection.component;

import com.giraffetech.decisivewizard.dependencyinjection.module.ScrollListFragmentModule;
import com.giraffetech.decisivewizard.fragment.ScrollListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ScrollListFragmentModule.class})
public interface ScrollListFragmentComponent {

    void inject(ScrollListFragment scrollListFragment);

}
