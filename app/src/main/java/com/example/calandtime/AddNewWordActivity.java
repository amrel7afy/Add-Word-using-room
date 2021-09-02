package com.example.calandtime;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
public class AddNewWordActivity extends AppCompatActivity {
EditText wordEditText,meaningEditText,typeEditText;
//view model to add new word Activty
    AddNewWordViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);
        wordEditText=findViewById(R.id.word_name);
        meaningEditText=findViewById(R.id.word_meaning);
        typeEditText=findViewById(R.id.word_type);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        mViewModel=new AddNewWordViewModel((Application) getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m =getMenuInflater();
        m.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_word:
                saveWord();
                return true;
            default: return super.onOptionsItemSelected(item);

        }
    }

    public void saveWord() {
        String word=wordEditText.getText().toString().trim();
        String meanig=meaningEditText.getText().toString().trim();
        String type=typeEditText.getText().toString().trim();
        if(word.isEmpty()||meanig.isEmpty()||type.isEmpty()){
            Toast.makeText(getApplicationContext(),
                    "please fill all fields",Toast.LENGTH_LONG).show();
           return;
        }
        mViewModel.insert(new Words(word,meanig,type));
        Log.i("TAG", "saveWord: "+word);
        finish();
    }
}