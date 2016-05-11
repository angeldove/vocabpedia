package ksk.com.vocabpedia.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import ksk.com.vocabpedia.model.Favourite;


public class FavDao extends AbstractDao<Favourite, Long> {

    public static final String TABLENAME = "FavRecord";

    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id",
                true, "_id");
        public final static Property WordId = new Property(1, Long.class, "wordid",
                true, "WordId");

    }

    ;

    public FavDao(DaoConfig config) {
        super(config);
    }

    public FavDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);

    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "'FavRecord' (" + //
                "'_id' INTEGER PRIMARY KEY," + "'WordId' INTEGER NOT NULL);"); // 5: taskstatus
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "")
                + "'FavRecord'";
        db.execSQL(sql);
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Favourite entity) {
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        Long wordId = entity.getWordId();
        if (wordId != null) {
            stmt.bindLong(2, wordId);
        }

    }

    @Override
    protected Long getKey(Favourite entity) {
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
    protected Favourite readEntity(Cursor cursor, int offset) {
        Favourite entity = new Favourite(
                //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1) // projectId

        );
        return entity;
    }

    @Override
    protected void readEntity(Cursor cursor, Favourite entity, int offset) {

        entity.setId(cursor.isNull(offset + 0) ? null : cursor
                .getLong(offset + 0));
        entity.setWordId(cursor.getLong(offset + 1));

    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected Long updateKeyAfterInsert(Favourite entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

}// TaskRecordDao
