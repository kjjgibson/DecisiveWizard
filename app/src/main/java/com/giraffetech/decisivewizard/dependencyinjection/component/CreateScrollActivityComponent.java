package com.giraffetech.decisivewizard.dependencyinjection.component;

import com.giraffetech.decisivewizard.activity.CreateScrollActivity;
import com.giraffetech.decisivewizard.dependencyinjection.module.CreateScrollActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CreateScrollActivityModule.class})
public interface CreateScrollActivityComponent {

    void inject(CreateScrollActivity createScrollActivity);

}
