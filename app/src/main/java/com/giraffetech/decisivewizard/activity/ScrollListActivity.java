package com.giraffetech.decisivewizard.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.giraffetech.decisivewizard.R;
import com.giraffetech.decisivewizard.fragment.ScrollListFragment;
import com.giraffetech.decisivewizard.model.Scroll;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollListActivity extends AppCompatActivity implements ScrollListFragment.OnListFragmentInteractionListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.fab) FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState == null) {
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, ScrollListFragment.newInstance())
                        .commit();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
    void onFabClicked(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
    //endregion Click Listeners
}
