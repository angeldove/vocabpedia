package ksk.com.vocabpedia.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import ksk.com.vocabpedia.model.Antonym;

public class AntDao extends AbstractDao<Antonym, Long> {

    public static final String TABLENAME = "AntRecord";

    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id",
                true, "_id");
        public final static Property Word1 = new Property(1, String.class, "word1",
                false, "Word1");
        public final static Property Word2 = new Property(2, String.class, "word2", false, "Word2");

    };

    public AntDao(DaoConfig config) {
        super(config);
    }

    public AntDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);

    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "'AntRecord' (" + //
                "'_id' INTEGER PRIMARY KEY," + "'Word1' INTEGER NOT NULL," + "'Word2' INTEGER NOT NULL);");
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "")
                + "'AntRecord'";
        db.execSQL(sql);
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Antonym entity) {
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String word1 = entity.getWord1();
        if (word1 != null) {
            stmt.bindString(2, word1);
        }
        String word2 = entity.getWord2();
        if (word2 != null) {
            stmt.bindString(3, word2);
        }

    }

    @Override
    protected Long getKey(Antonym entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

    @Override
    protected Antonym readEntity(Cursor cursor, int offset) {
        Antonym entity = new Antonym(
                //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1),
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2)

        );
        return entity;
    }

    @Override
    protected void readEntity(Cursor cursor, Antonym entity, int offset) {

        entity.setId(cursor.isNull(offset + 0) ? null : cursor
                .getLong(offset + 0));
        entity.setWord1(cursor.getString(offset + 1));
        entity.setWord2(cursor.getString(offset+2));
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected Long updateKeyAfterInsert(Antonym entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

}// TaskRecordDao
