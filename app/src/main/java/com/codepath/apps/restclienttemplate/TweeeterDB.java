package com.codepath.apps.restclienttemplate;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = TweeeterDB.NAME, version = TweeeterDB.VERSION)
public class TweeeterDB {

    public static final String NAME = "TweeeterDatabase";

    public static final int VERSION = 1;
}
