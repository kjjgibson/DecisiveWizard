package com.giraffetech.decisivewizard.dependencyinjection.component;

import com.giraffetech.decisivewizard.fragment.CreateScrollFragment;
import com.giraffetech.decisivewizard.dependencyinjection.module.CreateScrollFragmentModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CreateScrollFragmentModule.class})
public interface CreateScrollFragmentComponent {

    void inject(CreateScrollFragment createScrollFragment);

}
