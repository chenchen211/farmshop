package com.hnxy.farmshop.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GOODS_BEAN".
*/
public class GoodsBeanDao extends AbstractDao<GoodsBean, Long> {

    public static final String TABLENAME = "GOODS_BEAN";

    /**
     * Properties of entity GoodsBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Money_real = new Property(0, String.class, "money_real", false, "MONEY_REAL");
        public final static Property Money_original = new Property(1, String.class, "money_original", false, "MONEY_ORIGINAL");
        public final static Property Money_freight = new Property(2, String.class, "money_freight", false, "MONEY_FREIGHT");
        public final static Property Product_id = new Property(3, long.class, "product_id", true, "_id");
        public final static Property Product_name = new Property(4, String.class, "product_name", false, "PRODUCT_NAME");
        public final static Property Product_logo = new Property(5, String.class, "product_logo", false, "PRODUCT_LOGO");
        public final static Property Product_mark = new Property(6, String.class, "product_mark", false, "PRODUCT_MARK");
        public final static Property Product_num = new Property(7, int.class, "product_num", false, "PRODUCT_NUM");
        public final static Property Product_sellnum = new Property(8, int.class, "product_sellnum", false, "PRODUCT_SELLNUM");
    };


    public GoodsBeanDao(DaoConfig config) {
        super(config);
    }
    
    public GoodsBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GOODS_BEAN\" (" + //
                "\"MONEY_REAL\" TEXT," + // 0: money_real
                "\"MONEY_ORIGINAL\" TEXT," + // 1: money_original
                "\"MONEY_FREIGHT\" TEXT," + // 2: money_freight
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 3: product_id
                "\"PRODUCT_NAME\" TEXT," + // 4: product_name
                "\"PRODUCT_LOGO\" TEXT," + // 5: product_logo
                "\"PRODUCT_MARK\" TEXT," + // 6: product_mark
                "\"PRODUCT_NUM\" INTEGER NOT NULL ," + // 7: product_num
                "\"PRODUCT_SELLNUM\" INTEGER NOT NULL );"); // 8: product_sellnum
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GOODS_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GoodsBean entity) {
        stmt.clearBindings();
 
        String money_real = entity.getMoney_real();
        if (money_real != null) {
            stmt.bindString(1, money_real);
        }
 
        String money_original = entity.getMoney_original();
        if (money_original != null) {
            stmt.bindString(2, money_original);
        }
 
        String money_freight = entity.getMoney_freight();
        if (money_freight != null) {
            stmt.bindString(3, money_freight);
        }
        stmt.bindLong(4, entity.getProduct_id());
 
        String product_name = entity.getProduct_name();
        if (product_name != null) {
            stmt.bindString(5, product_name);
        }
 
        String product_logo = entity.getProduct_logo();
        if (product_logo != null) {
            stmt.bindString(6, product_logo);
        }
 
        String product_mark = entity.getProduct_mark();
        if (product_mark != null) {
            stmt.bindString(7, product_mark);
        }
        stmt.bindLong(8, entity.getProduct_num());
        stmt.bindLong(9, entity.getProduct_sellnum());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GoodsBean entity) {
        stmt.clearBindings();
 
        String money_real = entity.getMoney_real();
        if (money_real != null) {
            stmt.bindString(1, money_real);
        }
 
        String money_original = entity.getMoney_original();
        if (money_original != null) {
            stmt.bindString(2, money_original);
        }
 
        String money_freight = entity.getMoney_freight();
        if (money_freight != null) {
            stmt.bindString(3, money_freight);
        }
        stmt.bindLong(4, entity.getProduct_id());
 
        String product_name = entity.getProduct_name();
        if (product_name != null) {
            stmt.bindString(5, product_name);
        }
 
        String product_logo = entity.getProduct_logo();
        if (product_logo != null) {
            stmt.bindString(6, product_logo);
        }
 
        String product_mark = entity.getProduct_mark();
        if (product_mark != null) {
            stmt.bindString(7, product_mark);
        }
        stmt.bindLong(8, entity.getProduct_num());
        stmt.bindLong(9, entity.getProduct_sellnum());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 3);
    }    

    @Override
    public GoodsBean readEntity(Cursor cursor, int offset) {
        GoodsBean entity = new GoodsBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // money_real
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // money_original
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // money_freight
            cursor.getLong(offset + 3), // product_id
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // product_name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // product_logo
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // product_mark
            cursor.getInt(offset + 7), // product_num
            cursor.getInt(offset + 8) // product_sellnum
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GoodsBean entity, int offset) {
        entity.setMoney_real(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setMoney_original(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMoney_freight(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProduct_id(cursor.getLong(offset + 3));
        entity.setProduct_name(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setProduct_logo(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setProduct_mark(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setProduct_num(cursor.getInt(offset + 7));
        entity.setProduct_sellnum(cursor.getInt(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GoodsBean entity, long rowId) {
        entity.setProduct_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GoodsBean entity) {
        if(entity != null) {
            return entity.getProduct_id();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
