package com.giraffetech.decisivewizard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.dependencyinjection.component.DaggerScrollListActivityComponent;
import com.giraffetech.decisivewizard.dependencyinjection.module.ScrollListActivityModule;
import com.giraffetech.decisivewizard.fragment.ScrollCardsFragment;
import com.giraffetech.decisivewizard.fragment.ScrollListFragment;
import com.giraffetech.decisivewizard.listener.OnListFragmentInteractionListener;
import com.giraffetech.decisivewizard.model.Scroll;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollListActivity extends OrmActivity implements OnListFragmentInteractionListener {

    //region Constants
    private static final String LOG = ScrollListActivity.class.getCanonicalName();
    private static final int REQUEST_CODE_CREATE_SCROLL = 100;
    //endregion Constants

    //region Bound Views
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    //endregion Bound Views

    //region Dependencies
    @Inject
    protected ScrollListFragment mScrollListFragment;

    @Inject
    protected ScrollCardsFragment mScrollCardsFragment;
    //endregion Dependencies

    //region Fields
    private OnScrollUpdatedListener mScrollUpdatedListener;
    //endregion Fields

    //region Activity Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list);

        //Bind the views
        ButterKnife.bind(this);

        //Inject the dependencies
        DaggerScrollListActivityComponent.builder()
                .scrollListActivityModule(new ScrollListActivityModule())
                .build()
                .inject(this);

        setSupportActionBar(mToolbar);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState == null) {
                showScrollCardsFragment();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lists_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_toggle_scroll_view) {
            if (getFragmentManager().findFragmentByTag(ScrollListFragment.class.getName()) != null) {
                showScrollCardsFragment();
                item.setIcon(R.drawable.ic_list_white_36dp);
            } else {
                showScrollListFragment();
                item.setIcon(R.drawable.ic_view_agenda_white_36dp);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_CREATE_SCROLL:
                if (resultCode == RESULT_OK) {
                    Scroll scroll = Parcels.unwrap(data.getParcelableExtra(CreateScrollActivity.PARCEL_KEY_SCROLL));
                    mScrollUpdatedListener.onScrollUpdated(scroll);
                } else {
                    Log.e(LOG, "Unexpected resultCode when returning from creating a Scroll: " + resultCode);
                }
                break;
        }
    }

    //endregion Activity Lifecycle

    //region OnListFragmentInteractionListener Methods
    @Override
    public void onScrollSelected(Scroll scroll) {
        Intent intent = new Intent(this, CreateScrollActivity.class);
        intent.putExtra(CreateScrollActivity.PARCEL_KEY_SCROLL, Parcels.wrap(scroll));
        startActivityForResult(intent, REQUEST_CODE_CREATE_SCROLL);
    }
    //endregion OnListFragmentInteractionListener Methods

    //region Click Listeners
    @OnClick(R.id.fab)
    void onFabClicked() {
        Intent intent = new Intent(this, CreateScrollActivity.class);
        startActivityForResult(intent, REQUEST_CODE_CREATE_SCROLL);
    }
    //endregion Click Listeners

    //region Private Methods
    private void showScrollListFragment() {
        mScrollUpdatedListener = mScrollListFragment;

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mScrollListFragment, mScrollListFragment.getClass().getName())
                .commit();
    }

    private void showScrollCardsFragment() {
        mScrollUpdatedListener = mScrollCardsFragment;

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mScrollCardsFragment, mScrollCardsFragment.getClass().getName())
                .commit();
    }
    //endregion Private Methods

    public interface OnScrollUpdatedListener {

        void onScrollUpdated(Scroll scroll);

    }

}
