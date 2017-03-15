package com.giraffetech.decisivewizard.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.giraffetech.decisivewizard.contract.ScrollContract;
import com.giraffetech.decisivewizard.helper.DatabaseHelper;
import com.giraffetech.decisivewizard.BR;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.parceler.Parcel;

import java.sql.SQLException;
import java.util.ArrayList;

@Parcel
@DatabaseTable(tableName = ScrollContract.Scroll.TABLE_NAME)
public class Scroll extends BaseObservable {

    //region Constants
    private static final String LOG = Scroll.class.getCanonicalName();
    //endregion Constants

    //region DB Fields
    @DatabaseField(generatedId = true, columnName = ScrollContract.Scroll._ID)
    private long mDbId;

    @DatabaseField(columnName = ScrollContract.Scroll.COLUMN_NAME)
    private String mName;

    @DatabaseField(columnName = ScrollContract.Scroll.COLUMN_DESCRIPTION)
    private String mDescription;

    @DatabaseField(columnName = ScrollContract.Scroll.COLUMN_SCROLL_ITEMS, dataType = DataType.SERIALIZABLE)
    private ArrayList<String> mScrollItems = new ArrayList<>();
    //endregion DB Fields

    //region Constructors
    public Scroll() {

    }

    public Scroll(String name, String description, ArrayList<String> scrollItems) {
        mName = name;
        mDescription = description;
        mScrollItems = scrollItems;
    }
    //endregion Constructors

    //region Getters and Setters
    public long getDbId() {
        return mDbId;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
        notifyPropertyChanged(BR.description);
    }

    public ArrayList<String> getScrollItems() {
        return mScrollItems;
    }

    public void setScrollItems(ArrayList<String> scrollItems) {
        mScrollItems = scrollItems;
    }
    //endregion Getters and Setters

    //region DB Methods

    /**
     * Either create or update the Scroll based on the dbId.
     *
     * @param databaseHelper - A database helper used to access the database
     * @return True if the Scroll was successfully saved (created or updated)
     */
    public boolean save(DatabaseHelper databaseHelper) {
        boolean saved = false;
        try {
            Dao<Scroll, Integer> scrollDao = databaseHelper.getScrollDao();
            Dao.CreateOrUpdateStatus createOrUpdateStatus = scrollDao.createOrUpdate(this);
            saved = createOrUpdateStatus.isCreated() || createOrUpdateStatus.isUpdated();
        } catch (SQLException e) {
            Log.e(LOG, "Couldn't save the Scroll", e);
        }

        return saved;
    }

    /**
     * Get an ArrayList of all the Scrolls saved in the database
     *
     * @param databaseHelper - A database helper used to access the database
     * @return - An ArrayList of all Scrolls
     */
    public static ArrayList<Scroll> getScrolls(DatabaseHelper databaseHelper) {
        ArrayList<Scroll> scrolls = new ArrayList<>();
        try {
            Dao<Scroll, Integer> scrollDao = databaseHelper.getScrollDao();
            scrolls = (ArrayList<Scroll>) scrollDao.queryForAll();
        } catch (SQLException e) {
            Log.e(LOG, "Couldn't get Scrolls", e);
        }
        return scrolls;
    }
    //endregion DB Methods

}
