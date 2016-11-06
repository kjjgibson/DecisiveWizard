package com.giraffetech.decisivewizard.activity;

import android.support.v7.app.AppCompatActivity;

import com.giraffetech.decisivewizard.helper.DatabaseHelper;
import com.giraffetech.decisivewizard.interfaces.DatabaseAccessor;
import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Classes should extend this class if they want access to the Database
 * This class provides Singleton access to a DatabaseHelper which is used to access the Database
 * When the extending Activity is destroyed, the DatabaseHelper will be released
 */
public class OrmActivity extends AppCompatActivity implements DatabaseAccessor {

    private DatabaseHelper mDatabaseHelper = null;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDatabaseHelper != null) {
            OpenHelperManager.releaseHelper();
            mDatabaseHelper = null;
        }
    }

    public DatabaseHelper getHelper() {
        if (mDatabaseHelper == null) {
            mDatabaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return mDatabaseHelper;
    }

}
