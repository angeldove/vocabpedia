package ksk.com.vocabpedia.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.Property;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.internal.DaoConfig;
import ksk.com.vocabpedia.model.Word;

public class WordDao extends AbstractDao<Word, Long> {

    public final static String TABLENAME = "WordRecord";

    public static class Properties {
        public final static de.greenrobot.dao.Property Id = new Property(0, Long.class, "id",
                true, "ID");
        public final static Property Word = new Property(1, String.class,
                "word", false, "WORD");
        public final static Property Meaning = new Property(2, String.class,
                "meaning", false, "MEANING");
        public final static Property Sentence1 = new Property(3, String.class,
                "sentence1", false, "SENTENCE1");
        public final static Property Sentence2 = new Property(4, String.class,
                "sentence2", false, "SENTENCE2");
        public final static Property Sentence3 = new Property(5, String.class,
                "sentence3", false, "SENTENCE3");
        public final static Property Sentence4 = new Property(6, String.class,
                "sentence4", false, "SENTENCE4");
        public final static Property Sentence5 = new Property(7, String.class,
                "sentence5", false, "SENTENCE5");

    };

    public WordDao(DaoConfig config) {
        super(config);
    }

    public WordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }
    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {

        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "'WordRecord' (" + //
                "'ID' INTEGER PRIMARY KEY ," + "'WORD' TEXT NOT NULL ," + "'MEANING' TEXT NOT NULL ,"
                + "'SENTENCE1' TEXT NOT NULL ," + "'SENTENCE2' TEXT NOT NULL ,"
                + "'SENTENCE3' TEXT NOT NULL ," + "'SENTENCE4' TEXT NOT NULL ,"
                + "'SENTENCE5' TEXT NOT NULL );");
    }
    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "")
                + "'WordRecord'";
        db.execSQL(sql);
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Word entity) {
        Long id = entity.getId();

        if (id != null) {
            stmt.bindLong(1, id);
        }

        String wrd = entity.getWord();
        if (wrd != null) {
            stmt.bindString(2, wrd);
        }
        String meaning = entity.getMeaning();

        if (meaning != null) {
            stmt.bindString(3, meaning);
        }
        String sentence1 = entity.getSentence1();
        if (sentence1 != null) {
            stmt.bindString(4, sentence1);
        }
        String sentence2 = entity.getSentence2();
        if (sentence2 != null) {
            stmt.bindString(5, sentence2);
        }
        String sentence3 = entity.getSentence3();
        if (sentence3 != null) {
            stmt.bindString(6, sentence3);
        }
        String sentence4 = entity.getSentence4();
        if (sentence4 != null) {
            stmt.bindString(7, sentence4);
        }
        String sentence5 = entity.getSentence5();
        if (sentence5 != null) {
            stmt.bindString(8, sentence5);
        }

    }

    @Override
    protected Long getKey(Word entity) {
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
    protected Word readEntity(Cursor cursor, int offset) {
        Word entity = new Word(

                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1),//word
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2),
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3),
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4),
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5),
                cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6),
                cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));

        return entity;
    }

    @Override
    protected void readEntity(Cursor cursor, Word entity, int offset) {
        entity.setId((cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0)));
        entity.setWord(cursor.getString(offset + 1));
        entity.setMeaning(cursor.getString(offset + 2));
        entity.setSentence1(cursor.getString(offset + 3));
        entity.setSentence2(cursor.getString(offset + 4));
        entity.setSentence3(cursor.getString(offset + 5));
        entity.setSentence4(cursor.getString(offset + 6));
        entity.setSentence5(cursor.getString(offset + 7));
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected Long updateKeyAfterInsert(Word entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

}// WordDao
