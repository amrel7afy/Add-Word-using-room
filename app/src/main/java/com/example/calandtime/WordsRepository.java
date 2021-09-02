package com.example.calandtime;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;

public class WordsRepository {
    private WordsDao mWordDao;
    private LiveData<List<Words>>getAllWords;

    public WordsRepository(Application app) {
       WordRoomDb db =WordRoomDb.getInstance(app);
       mWordDao =db.wordsDao();
       getAllWords= mWordDao.getAllWords();
    }

    public void insert(Words words){
        new InsertAsyncTask(mWordDao).execute(words);
    }
    public void delete(Words words){
        new DeleteAsyncTask(mWordDao).execute(words);
    }
    public void update(Words words){
        new UpdateAsyncTask(mWordDao).execute(words);
    }

    public LiveData<List<Words>> getAllWords(){
        return getAllWords;
    }
    public void deleteAllWords(){
    new  DeleteAllWordsAsyncTask(mWordDao).execute();
    }

private static class InsertAsyncTask extends AsyncTask<Words,Void,Void>{
    private WordsDao mWordsDao;
    public  InsertAsyncTask(WordsDao wordsDao){
        mWordsDao=wordsDao;
    }

    @Override
    protected Void doInBackground(Words... words) {
        mWordsDao.insert(words[0]);
        return null;
    }
}
private static class DeleteAsyncTask extends AsyncTask<Words,Void,Void>{
        private WordsDao mWordsDao;
        public DeleteAsyncTask(WordsDao wordsDao){
            mWordsDao=wordsDao;
        }

        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.delete(words[0]);
            return null;
        }
    }
private static class UpdateAsyncTask extends AsyncTask<Words,Void,Void>{
        private WordsDao mWordsDao;
        public UpdateAsyncTask(WordsDao wordsDao){
            mWordsDao=wordsDao;
        }

        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.update(words[0]);
            return null;
        }
    }
private static class DeleteAllWordsAsyncTask extends AsyncTask<Void,Void,Void>{
        private WordsDao mWordsDao;
        public DeleteAllWordsAsyncTask(WordsDao wordsDao){
            mWordsDao=wordsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.deleteAllwords();
            return null;
        }
    }
}
