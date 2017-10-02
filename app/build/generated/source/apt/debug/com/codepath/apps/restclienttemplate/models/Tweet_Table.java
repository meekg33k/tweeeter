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
public final class Tweet_Table extends ModelAdapter<Tweet> {
  /**
   * Primary Key */
  public static final Property<Long> id = new Property<Long>(Tweet.class, "id");

  public static final Property<String> timestamp = new Property<String>(Tweet.class, "timestamp");

  public static final Property<String> body = new Property<String>(Tweet.class, "body");

  /**
   * Foreign Key */
  public static final Property<Long> user_id = new Property<Long>(Tweet.class, "user_id");

  public static final IProperty[] ALL_COLUMN_PROPERTIES = new IProperty[]{id,timestamp,body,user_id};

  public Tweet_Table(DatabaseDefinition databaseDefinition) {
    super(databaseDefinition);
  }

  @Override
  public final Class<Tweet> getModelClass() {
    return Tweet.class;
  }

  @Override
  public final String getTableName() {
    return "`Tweet`";
  }

  @Override
  public final Tweet newInstance() {
    return new Tweet();
  }

  @Override
  public final Property getProperty(String columnName) {
    columnName = QueryBuilder.quoteIfNeeded(columnName);
    switch ((columnName)) {
      case "`id`":  {
        return id;
      }
      case "`timestamp`":  {
        return timestamp;
      }
      case "`body`":  {
        return body;
      }
      case "`user_id`": {
        return user_id;
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
  public final void bindToInsertValues(ContentValues values, Tweet model) {
    values.put("`id`", model.id != null ? model.id : null);
    values.put("`timestamp`", model.timestamp != null ? model.timestamp : null);
    values.put("`body`", model.body != null ? model.body : null);
    if (model.user != null) {
      values.put("`user_id`", model.user.id);
    } else {
      values.putNull("`user_id`");
    }
  }

  @Override
  public final void bindToInsertStatement(DatabaseStatement statement, Tweet model, int start) {
    statement.bindNumberOrNull(1 + start, model.id);
    statement.bindStringOrNull(2 + start, model.timestamp);
    statement.bindStringOrNull(3 + start, model.body);
    if (model.user != null) {
      statement.bindNumberOrNull(4 + start, model.user.id);
    } else {
      statement.bindNull(4 + start);
    }
  }

  @Override
  public final void bindToUpdateStatement(DatabaseStatement statement, Tweet model) {
    statement.bindNumberOrNull(1, model.id);
    statement.bindStringOrNull(2, model.timestamp);
    statement.bindStringOrNull(3, model.body);
    if (model.user != null) {
      statement.bindNumberOrNull(4, model.user.id);
    } else {
      statement.bindNull(4);
    }
    statement.bindNumberOrNull(5, model.id);
  }

  @Override
  public final void bindToDeleteStatement(DatabaseStatement statement, Tweet model) {
    statement.bindNumberOrNull(1, model.id);
  }

  @Override
  public final String getCompiledStatementQuery() {
    return "INSERT INTO `Tweet`(`id`,`timestamp`,`body`,`user_id`) VALUES (?,?,?,?)";
  }

  @Override
  public final String getUpdateStatementQuery() {
    return "UPDATE `Tweet` SET `id`=?,`timestamp`=?,`body`=?,`user_id`=? WHERE `id`=?";
  }

  @Override
  public final String getDeleteStatementQuery() {
    return "DELETE FROM `Tweet` WHERE `id`=?";
  }

  @Override
  public final String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `Tweet`(`id` INTEGER, `timestamp` TEXT, `body` TEXT, `user_id` INTEGER, PRIMARY KEY(`id`)"+ ", FOREIGN KEY(`user_id`) REFERENCES " + com.raizlabs.android.dbflow.config.FlowManager.getTableName(com.codepath.apps.restclienttemplate.models.User.class) + "(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION" + ");";
  }

  @Override
  public final void loadFromCursor(FlowCursor cursor, Tweet model) {
    model.id = cursor.getLongOrDefault("id");
    model.timestamp = cursor.getStringOrDefault("timestamp");
    model.body = cursor.getStringOrDefault("body");
    int index_user_id_User_Table = cursor.getColumnIndex("user_id");
    if (index_user_id_User_Table != -1 && !cursor.isNull(index_user_id_User_Table)) {
      model.user = SQLite.select().from(User.class).where()
          .and(User_Table.id.eq(cursor.getLong(index_user_id_User_Table)))
          .querySingle();
    } else {
      model.user = null;
    }
  }

  @Override
  public final boolean exists(Tweet model, DatabaseWrapper wrapper) {
    return SQLite.selectCountOf()
    .from(Tweet.class)
    .where(getPrimaryConditionClause(model))
    .hasData(wrapper);
  }

  @Override
  public final OperatorGroup getPrimaryConditionClause(Tweet model) {
    OperatorGroup clause = OperatorGroup.clause();
    clause.and(id.eq(model.id));
    return clause;
  }
}
