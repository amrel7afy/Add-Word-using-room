package com.example.calandtime;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AddNewWordViewModel extends AndroidViewModel {
    private WordsRepository mRepository;

    public AddNewWordViewModel(@NonNull Application application) {
        super(application);
        mRepository=new WordsRepository(application);

    }

    public void insert(Words words){
        mRepository.insert(words);
    }

    public void update(Words words){
        mRepository.update(words);
    }


}
