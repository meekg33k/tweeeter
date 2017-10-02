package com.raizlabs.android.dbflow.config;

import com.codepath.apps.restclienttemplate.TweeeterDB;
import com.codepath.apps.restclienttemplate.models.SampleModel_Table;
import com.codepath.apps.restclienttemplate.models.Tweet_Table;
import com.codepath.apps.restclienttemplate.models.User_Table;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;

/**
 * This is generated code. Please do not modify */
public final class TweeeterDBTweeeterDatabase_Database extends DatabaseDefinition {
  public TweeeterDBTweeeterDatabase_Database(DatabaseHolder holder) {
    addModelAdapter(new SampleModel_Table(this), holder);
    addModelAdapter(new Tweet_Table(this), holder);
    addModelAdapter(new User_Table(this), holder);
  }

  @Override
  public final Class<?> getAssociatedDatabaseClassFile() {
    return TweeeterDB.class;
  }

  @Override
  public final boolean isForeignKeysSupported() {
    return false;
  }

  @Override
  public final boolean isInMemory() {
    return false;
  }

  @Override
  public final boolean backupEnabled() {
    return false;
  }

  @Override
  public final boolean areConsistencyChecksEnabled() {
    return false;
  }

  @Override
  public final int getDatabaseVersion() {
    return 1;
  }

  @Override
  public final String getDatabaseName() {
    return "TweeeterDatabase";
  }
}
