package com.bw.week2_one.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bw.week2_one.model.dao.LeftDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LEFT_DAO".
*/
public class LeftDaoDao extends AbstractDao<LeftDao, Void> {

    public static final String TABLENAME = "LEFT_DAO";

    /**
     * Properties of entity LeftDao.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property LeftDaoJson = new Property(0, String.class, "leftDaoJson", false, "LEFT_DAO_JSON");
    }


    public LeftDaoDao(DaoConfig config) {
        super(config);
    }
    
    public LeftDaoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LEFT_DAO\" (" + //
                "\"LEFT_DAO_JSON\" TEXT);"); // 0: leftDaoJson
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LEFT_DAO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LeftDao entity) {
        stmt.clearBindings();
 
        String leftDaoJson = entity.getLeftDaoJson();
        if (leftDaoJson != null) {
            stmt.bindString(1, leftDaoJson);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LeftDao entity) {
        stmt.clearBindings();
 
        String leftDaoJson = entity.getLeftDaoJson();
        if (leftDaoJson != null) {
            stmt.bindString(1, leftDaoJson);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public LeftDao readEntity(Cursor cursor, int offset) {
        LeftDao entity = new LeftDao( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0) // leftDaoJson
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LeftDao entity, int offset) {
        entity.setLeftDaoJson(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(LeftDao entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(LeftDao entity) {
        return null;
    }

    @Override
    public boolean hasKey(LeftDao entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
