package com.giraffetech.decisivewizard.dependencyinjection.component;

import com.giraffetech.decisivewizard.activity.ScrollListActivity;
import com.giraffetech.decisivewizard.dependencyinjection.module.ScrollListActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ScrollListActivityModule.class})
public interface ScrollListActivityComponent {

    void inject(ScrollListActivity scrollListActivity);

}
