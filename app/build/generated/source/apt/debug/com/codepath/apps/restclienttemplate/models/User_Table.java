package com.codepath.apps.restclienttemplate.models;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;

/**
 * This is generated code. Please do not modify */
public final class User_Table extends ModelAdapter<User> {
  /**
   * Primary Key */
  public static final Property<Long> id = new Property<Long>(User.class, "id");

  public static final Property<String> name = new Property<String>(User.class, "name");

  public static final Property<String> screenName = new Property<String>(User.class, "screenName");

  public static final Property<String> profileImageURL = new Property<String>(User.class, "profileImageURL");

  public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{id,name,screenName,profileImageURL};

  public User_Table(DatabaseDefinition databaseDefinition) {
    super(databaseDefinition);
  }

  @Override
  public final Class<User> getModelClass() {
    return User.class;
  }

  @Override
  public final String getTableName() {
    return "`User`";
  }

  @Override
  public final User newInstance() {
    return new User();
  }

  @Override
  public final Property getProperty(String columnName) {
    columnName = QueryBuilder.quoteIfNeeded(columnName);
    switch ((columnName)) {
      case "`id`":  {
        return id;
      }
      case "`name`":  {
        return name;
      }
      case "`screenName`":  {
        return screenName;
      }
      case "`profileImageURL`":  {
        return profileImageURL;
      }
      default: {
        throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
      }
    }
  }

  @Override
  public final IProperty[] getAllColumnProperties() {
    return ALL_COLUMN_PROPERTIES;
  }

  @Override
  public final void bindToInsertValues(ContentValues values, User model) {
    values.put("`id`", model.id != null ? model.id : null);
    values.put("`name`", model.name != null ? model.name : null);
    values.put("`screenName`", model.screenName != null ? model.screenName : null);
    values.put("`profileImageURL`", model.profileImageURL != null ? model.profileImageURL : null);
  }

  @Override
  public final void bindToInsertStatement(DatabaseStatement statement, User model, int start) {
    statement.bindNumberOrNull(1 + start, model.id);
    statement.bindStringOrNull(2 + start, model.name);
    statement.bindStringOrNull(3 + start, model.screenName);
    statement.bindStringOrNull(4 + start, model.profileImageURL);
  }

  @Override
  public final void bindToUpdateStatement(DatabaseStatement statement, User model) {
    statement.bindNumberOrNull(1, model.id);
    statement.bindStringOrNull(2, model.name);
    statement.bindStringOrNull(3, model.screenName);
    statement.bindStringOrNull(4, model.profileImageURL);
    statement.bindNumberOrNull(5, model.id);
  }

  @Override
  public final void bindToDeleteStatement(DatabaseStatement statement, User model) {
    statement.bindNumberOrNull(1, model.id);
  }

  @Override
  public final String getCompiledStatementQuery() {
    return "INSERT INTO `User`(`id`,`name`,`screenName`,`profileImageURL`) VALUES (?,?,?,?)";
  }

  @Override
  public final String getUpdateStatementQuery() {
    return "UPDATE `User` SET `id`=?,`name`=?,`screenName`=?,`profileImageURL`=? WHERE `id`=?";
  }

  @Override
  public final String getDeleteStatementQuery() {
    return "DELETE FROM `User` WHERE `id`=?";
  }

  @Override
  public final String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `User`(`id` INTEGER, `name` TEXT, `screenName` TEXT, `profileImageURL` TEXT, PRIMARY KEY(`id`))";
  }

  @Override
  public final void loadFromCursor(FlowCursor cursor, User model) {
    model.id = cursor.getLongOrDefault("id");
    model.name = cursor.getStringOrDefault("name");
    model.screenName = cursor.getStringOrDefault("screenName");
    model.profileImageURL = cursor.getStringOrDefault("profileImageURL");
  }

  @Override
  public final boolean exists(User model, DatabaseWrapper wrapper) {
    return SQLite.selectCountOf()
    .from(User.class)
    .where(getPrimaryConditionClause(model))
    .hasData(wrapper);
  }

  @Override
  public final OperatorGroup getPrimaryConditionClause(User model) {
    OperatorGroup clause = OperatorGroup.clause();
    clause.and(id.eq(model.id));
    return clause;
  }
}
