package com.example.calandtime;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
  private List<Words> mWordsList=new ArrayList<>();
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView= LayoutInflater.from(parent.getContext())
               .inflate(R.layout.word_list_item,parent,false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
    Words currentWord=mWordsList.get(position);
    holder.textViewWord.setText(currentWord.getWordName());
    holder.textViewMeaning.setText(currentWord.getWordMeaning());
    holder.textViewType.setText(currentWord.getWordType());
    }

public void setmWords(List<Words>words){
        mWordsList=words;
        notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        return mWordsList.size();
    }

    public static class  WordViewHolder extends RecyclerView.ViewHolder{
public TextView textViewWord;
public TextView textViewMeaning;
public TextView textViewType;
        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWord=itemView.findViewById(R.id.word_text_view);
            textViewMeaning=itemView.findViewById(R.id.meaning_text_view);
            textViewType=itemView.findViewById(R.id.type_text_view);
        }
    }
}
