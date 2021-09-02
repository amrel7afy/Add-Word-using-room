package com.example.calandtime;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Words.class},version = 1)
public abstract class WordRoomDb extends RoomDatabase {
    //singlton
    private static WordRoomDb instance;

    public abstract WordsDao wordsDao();

    //singlton
    public static synchronized WordRoomDb getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , WordRoomDb.class
                    , "word-database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCAllBack).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCAllBack = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDateAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
    private static class PopulateDateAsyncTask extends AsyncTask<Void,Void,Void>{
        private WordsDao mWordsDao;
        PopulateDateAsyncTask (WordRoomDb db){
            mWordsDao=db.wordsDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.insert(new Words("book","Book","noun"));
            mWordsDao.insert(new Words("book","Book","noun"));
            mWordsDao.insert(new Words("book","Book","noun"));
            return null;
        }
    }
}
