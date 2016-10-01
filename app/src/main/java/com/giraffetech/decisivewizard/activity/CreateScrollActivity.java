package com.giraffetech.decisivewizard.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.dependencyinjection.component.DaggerCreateScrollActivityComponent;
import com.giraffetech.decisivewizard.dependencyinjection.module.CreateScrollActivityModule;
import com.giraffetech.decisivewizard.fragment.CreateScrollFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateScrollActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Inject
    protected CreateScrollFragment mCreateScrollFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_scroll);

        //Bind the views
        ButterKnife.bind(this);

        //Inject the dependencies
        DaggerCreateScrollActivityComponent.builder()
                .createScrollActivityModule(new CreateScrollActivityModule())
                .build()
                .inject(this);

        //Setup the Toolbar
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_36dp));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Setup the Fragment
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState == null) {
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, mCreateScrollFragment, mCreateScrollFragment.getClass().getName())
                        .commit();
            }
        }
    }

}
