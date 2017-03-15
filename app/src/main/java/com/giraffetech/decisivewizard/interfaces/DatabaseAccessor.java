package com.giraffetech.decisivewizard.interfaces;

import com.giraffetech.decisivewizard.helper.DatabaseHelper;

// Classes that implement this Interface must provide access to a DatabaseHelper instance
// Implementing classes should also be responsible for managing the lifecycle of the DatabaseHelper
public interface DatabaseAccessor {

    /**
     * @return A DatabaseHelper used to access the Database
     */
    DatabaseHelper getHelper();

}
