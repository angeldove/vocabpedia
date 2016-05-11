package ksk.com.vocabpedia.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ksk.com.vocabpedia.model.Antonym;
import ksk.com.vocabpedia.model.Favourite;
import ksk.com.vocabpedia.model.Word;

public class DatabaseOperations {

    private static DatabaseOperations instance;
    private SQLiteDatabase sQLiteDatabase;

    WordDao worddao;
    FavDao favdao;

    public static synchronized DatabaseOperations getInstance() {

        if (instance == null) {
            instance = new DatabaseOperations();
        }

        return instance;
    }

    public DaoSession getSession() {
        sQLiteDatabase = DatabaseManager.getInstance().openDatabase();
        DaoMaster daoMaster = new DaoMaster(sQLiteDatabase);
        return daoMaster.newSession();
    }

    public void closeSession() {
        DatabaseManager.getInstance().closeDatabase();
    }

    public void insert(Word entity) {
        getSession().getwordDao().insert(entity);
        closeSession();
    }

    public void insertFav(Favourite entity) {
        getSession().getFavDao().insert(entity);
        closeSession();
    }

    public void insertAnt(Antonym entity) {
        getSession().getAntDao().insert(entity);
        closeSession();

    }

    public void deleteWord(Long key) {
        getSession().getwordDao().deleteByKey(key);
        closeSession();
    }

    public void deleteFav(Long key) {
        getSession().getFavDao().deleteByKey(key);
        closeSession();
        getAllDataFromDB();
    }

    public void deleteAnt(Long key) {
        getSession().getAntDao().deleteByKey(key);
        closeSession();
        ;
        getAllDataFromDB();


    }

    public List<Word> getDataById(long key) {

        worddao = getSession().getwordDao();

        List<Word> infoArray = worddao.queryBuilder().where(WordDao.Properties.Id.like("%" + key + "%")).list();

        return infoArray;
    }

    public void UpdateData(Word entity) {
        getSession().getwordDao().update(entity);
        closeSession();
        getAllDataFromDB();
    }

    public void UpdateFav(Favourite entity) {
        getSession().getFavDao().update(entity);
        closeSession();
        getAllDataFromDB();
    }

    public void UpdateAnt(Antonym entity) {
        getSession().getAntDao().update(entity);
        closeSession();
        getAllDataFromDB();
    }

    public ArrayList<Word> getAllDataFromDB() {

        ArrayList<Word> array = new ArrayList<Word>();
        worddao = getSession().getwordDao();

        List<Word> projectArray = worddao.queryBuilder().list();

        for (int i = 0; i < projectArray.size(); i++) {
            Word data = new Word();
            data.setId(projectArray.get(i).getId());
            data.setWord(projectArray.get(i).getWord());
            data.setMeaning(projectArray.get(i).getMeaning());
            data.setSentence1(projectArray.get(i).getSentence1());
            data.setSentence2(projectArray.get(i).getSentence2());
            data.setSentence3(projectArray.get(i).getSentence3());
            data.setSentence4(projectArray.get(i).getSentence4());
            data.setSentence5(projectArray.get(i).getSentence5());

            array.add(data);
        }
        closeSession();
        return array;
    }

    public ArrayList<Favourite> getAllFavFromDB() {
        FavDao stepDao = getSession().getFavDao();

        ArrayList<Favourite> favList = new ArrayList<Favourite>();

        List<Favourite> stepArray = stepDao.queryBuilder().list();

        for (int i = 0; i < stepArray.size(); i++) {
            Favourite data = new Favourite();
            data.setId(stepArray.get(i).getId());
            data.setWordId(stepArray.get(i).getWordId());
            favList.add(data);
        }
        closeSession();
        return favList;
    }

    // getting the total task done by client id
/*	public int getTaskDoneCountByClient(Long clientId) {

		int count = 0;

		FavDao stepDao = getSession().getFavDao();

		List<Favourite> stepArray = stepDao.queryBuilder()
				.where(FavDao.Properties.WordId.like("%" + clientId + "%"))
				.list();

		for (int i = 0; i < stepArray.size(); i++) {

			if (stepArray.get(i).getId() == 3)
				count++;

		}
		closeSession();
		return count;
	}*/
    // getting the total task under process by client id
    /*	public int getTaskDoingCountByClient(Long clientId) {

			int count = 0;

			FavDao stepDao = getSession().getFavDao();

			List<Favourite> stepArray = stepDao.queryBuilder()
					.where(FavDao.Properties.WordId.like("%" + clientId + "%"))
					.list();

			for (int i = 0; i < stepArray.size(); i++) {

				if (stepArray.get(i).getTask() == 2)
					count++;

			}
			closeSession();
			return count;
		}*/
    // getting the total task under process by client id
                /*public int getTaskTdoCountByClient(Long clientId) {

					int count = 0;

					FavDao stepDao = getSession().getTaskDao();

					List<Tasks> stepArray = stepDao.queryBuilder()
							.where(FavDao.Properties.ProjectId.like("%" + clientId + "%"))
							.list();

					for (int i = 0; i < stepArray.size(); i++) {

						if (stepArray.get(i).getTask() == 1)
							count++;

					}
					closeSession();
					return count;
				}
*/
    // getting all tasks by client id
	/*public ArrayList<Tasks> getAllTasksByProjectId(Long projectId) {

		FavDao stepDao = getSession().getTaskDao();

		ArrayList<Tasks> stepList = new ArrayList<Tasks>();

		List<Tasks> stepArray = stepDao.queryBuilder()
				.where(FavDao.Properties.ProjectId.like("%" + projectId + "%"))
				.list();

		for (int i = 0; i < stepArray.size(); i++) {
			Tasks task = new Tasks();

			task.setId(stepArray.get(i).getId());
			task.setProjectId(stepArray.get(i).getProjectId());
			task.setTitle(stepArray.get(i).getTitle());
			task.setTask(stepArray.get(i).getTask());
			stepList.add(task);
		}
		closeSession();
		return stepList;
	}*/

    // getting word info by wordId
    public ArrayList<Word> getWordByWordId(int wordId) {

        WordDao stepDao = getSession().getwordDao();

        ArrayList<Word> List = new ArrayList<>();

        List<Word> stepArray = stepDao.queryBuilder()
                .where(WordDao.Properties.Id.like("%" + wordId + "%"))
                .list();

        for (int i =1; i <= stepArray.size(); i++) {
            Word task = new Word();

            task.setId(stepArray.get(i).getId());
            task.setWord(stepArray.get(i).getWord());
            task.setMeaning(stepArray.get(i).getMeaning());
            task.setSentence1(stepArray.get(i).getSentence1());
            task.setSentence2(stepArray.get(i).getSentence2());
            task.setSentence3(stepArray.get(i).getSentence3());
            task.setSentence4(stepArray.get(i).getSentence4());
            task.setSentence5(stepArray.get(i).getSentence5());
            List.add(task);
        }
        closeSession();
        return List;
    }

}// DatabaseOperations
