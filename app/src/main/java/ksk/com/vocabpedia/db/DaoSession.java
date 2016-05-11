
package ksk.com.vocabpedia.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;
import ksk.com.vocabpedia.model.Antonym;
import ksk.com.vocabpedia.model.Favourite;
import ksk.com.vocabpedia.model.Word;

public class DaoSession extends AbstractDaoSession {

    private final DaoConfig WordDaoConfig;
    private final DaoConfig FavDaoConfig;
    private final DaoConfig AntDaoConfig;

    private final WordDao wordDao;

    private final FavDao favDao;

    private final AntDao  antDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type,
                      Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> daoConfigMap) {
        super(db);

        WordDaoConfig = daoConfigMap.get(WordDao.class).clone();
        WordDaoConfig.initIdentityScope(type);

        FavDaoConfig = daoConfigMap.get(FavDao.class).clone();
        FavDaoConfig.initIdentityScope(type);

        AntDaoConfig = daoConfigMap.get(AntDao.class).clone();
        AntDaoConfig.initIdentityScope(type);

        wordDao = new WordDao(WordDaoConfig, this);
        favDao = new FavDao(FavDaoConfig, this);
        antDao = new AntDao(AntDaoConfig, this);

        registerDao(Word.class, wordDao);
        registerDao(Favourite.class, favDao);
        registerDao(Antonym.class, antDao);

    }

    public void clear() {
        WordDaoConfig.getIdentityScope().clear();
        FavDaoConfig.getIdentityScope().clear();
        AntDaoConfig.getIdentityScope().clear();
    }

    public WordDao getwordDao() {
        return wordDao;
    }

    public FavDao getFavDao() {
        return favDao;
    }

    public AntDao getAntDao(){ return antDao; }
}//DaoSession
