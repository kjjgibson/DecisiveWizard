package com.giraffetech.decisivewizard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.dependencyinjection.component.DaggerScrollListActivityComponent;
import com.giraffetech.decisivewizard.dependencyinjection.module.ScrollListActivityModule;
import com.giraffetech.decisivewizard.fragment.ScrollCardsFragment;
import com.giraffetech.decisivewizard.fragment.ScrollListFragment;
import com.giraffetech.decisivewizard.listener.OnListFragmentInteractionListener;
import com.giraffetech.decisivewizard.model.Scroll;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollListActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.fab) FloatingActionButton mFab;

    @Inject
    protected ScrollListFragment mScrollListFragment;

    @Inject
    protected ScrollCardsFragment mScrollCardsFragment;

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
    public void onScrollSelected(Scroll scroll) {
        Toast.makeText(this, "Scroll selected: " + scroll.getName(), Toast.LENGTH_LONG).show();
    }

    //region Click Listeners
    @OnClick(R.id.fab)
    void onFabClicked() {
        Intent intent = new Intent(this, CreateScrollActivity.class);
        startActivity(intent);
    }
    //endregion Click Listeners

    private void showScrollListFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mScrollListFragment, mScrollListFragment.getClass().getName())
                .commit();
    }

    private void showScrollCardsFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mScrollCardsFragment, mScrollCardsFragment.getClass().getName())
                .commit();
    }
}
