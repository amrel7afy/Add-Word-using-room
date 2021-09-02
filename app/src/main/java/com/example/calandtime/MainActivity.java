package com.example.calandtime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  {
private  WordViewModel mWordViewModel;
private RecyclerView mRecyclerView;
private  WordAdapter mWordAdapter;
private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton=findViewById(R.id.add_word);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),
                        AddNewWordActivity.class);
                startActivityForResult(intent,1);
            }
        });
        //recycler view
        mRecyclerView=findViewById(R.id.words_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        //connect Recyclerview with adapter
        mWordAdapter=new WordAdapter();
        mRecyclerView.setAdapter(mWordAdapter);
        //View model
          mWordViewModel= new WordViewModel((Application) getApplicationContext());
          mWordViewModel.getAllWords().observe(this, new Observer<List<Words>>() {
              @Override
              public void onChanged(List<Words> words) {
                  mWordAdapter.setmWords(words);
                  Toast.makeText(getApplicationContext(),"onChanged worked",Toast.LENGTH_SHORT).show();
              }
          });
    }
}

