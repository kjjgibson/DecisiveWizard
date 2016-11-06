package com.giraffetech.decisivewizard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.dependencyinjection.component.DaggerCreateScrollActivityComponent;
import com.giraffetech.decisivewizard.dependencyinjection.module.CreateScrollActivityModule;
import com.giraffetech.decisivewizard.fragment.CreateScrollFragment;
import com.giraffetech.decisivewizard.model.Scroll;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateScrollActivity extends OrmActivity {

    //region Constants
    //String used as a key when getting the Scroll from the Activity Intent
    public static final String PARCEL_KEY_SCROLL = "scroll";
    //endregion Constants

    //region Bound Views
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    //endregion Bound Views

    //region Fields
    //Either an existing Scroll that we are updating, or a new Scroll that we are creating
    private Scroll mScroll;
    //endregion Fields

    //region Dependencies
    @Inject
    protected CreateScrollFragment mCreateScrollFragment;
    //endregion Dependencies

    //region Activity Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_scroll);

        //Setup any args passed in the Activity Intent
        mScroll = Parcels.unwrap(getIntent().getParcelableExtra(PARCEL_KEY_SCROLL));
        //If we haven't been passed a Scroll then we aren't updating and we should create one
        if (mScroll == null) {
            mScroll = new Scroll();
        }

        //Bind the views
        ButterKnife.bind(this);

        //Inject the dependencies
        DaggerCreateScrollActivityComponent.builder()
                .createScrollActivityModule(new CreateScrollActivityModule(mScroll))
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

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        //Get the updated Scroll from the Fragment
        mScroll = mCreateScrollFragment.getScroll();

        //Send the Scroll back to whomever started this Activity
        // so that they can update their display in the case of a created or updated Scroll
        //This needs to be done before the super call to onBackPressed as it must be done before finish()
        Intent intent = getIntent();
        intent.putExtra(PARCEL_KEY_SCROLL, Parcels.wrap(mScroll));
        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }
    //endregion Activity Lifecycle

}
