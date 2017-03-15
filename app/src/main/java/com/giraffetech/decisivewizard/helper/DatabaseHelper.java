package com.giraffetech.decisivewizard.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.giraffetech.decisivewizard.model.Scroll;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper  extends OrmLiteSqliteOpenHelper {

    //region Constants
    private static final String DATABASE_NAME = "decisive_wizard.db";
    private static final int DATABASE_VERSION = 1;
    private static final String LOG = DatabaseHelper.class.getName();
    //endregion Constants

    //region Fields
    private Dao<Scroll, Integer> mScrollDao = null;
    //endregion Fields

    //region Constructors
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //endregion Constructors

    //region Overridden Methods
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Scroll.class);
        } catch (SQLException e) {
            Log.e(LOG, "Can't create table", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        mScrollDao = null;
    }
    //endregion Overridden Methods

    //region Dao Methods
    public Dao<Scroll, Integer> getScrollDao() throws SQLException {
        if (mScrollDao == null) {
            mScrollDao = getDao(Scroll.class);
        }
        return mScrollDao;
    }
    //endregion Dao Methods

}