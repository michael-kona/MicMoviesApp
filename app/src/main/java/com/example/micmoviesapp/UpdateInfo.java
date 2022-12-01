package com.example.micmoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UpdateInfo extends AppCompatActivity {
    TextView welcome_tv;
    Button btn_insert, btn_retrieve, btn_update,btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        DbHelper dbHelper=new DbHelper(getApplicationContext());
        welcome_tv=(TextView) findViewById(R.id.welcome_tv);
        btn_insert=(Button)findViewById(R.id.btn_insert);
        btn_retrieve=(Button)findViewById(R.id.btn_retrieve);
        btn_update=(Button)findViewById(R.id.btn_update);
        btn_delete=(Button)findViewById(R.id.btn_delete);
        Intent inext=getIntent();
        String user=inext.getStringExtra("username");
        welcome_tv.setText(user + welcome_tv.getText().toString());
        btn_insert.setOnClickListener(new View.OnClickListener() {
            ArrayList<Movies> a = new ArrayList<>();
            String[] names = {"Flywheel","Godgilla","Jurassic Park","Left Behind","Passion of Christ","Ben Hur","Courageous","Letters to God","Encounter","Grace Unplugged"};
            @Override
            public void onClick(View view) {
                for(int i=0;i< names.length;i++) {
                    Movies movie = new Movies();
                    movie.name = names[i];
                    movie.date = "28th Nov";
                    movie.time = "noon show";
                    movie.filled = 10;
                    movie.available = 100;
                    try {
                        a.add(movie);
                        dbHelper.addMovie(movie);
                    }catch (Exception ex){
                        Log.d("RUNTIME ERROR", "onClick: "+ex.getMessage());
                    }
                }

            }
        });
        btn_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Movies> movie_details=dbHelper.getMovies();
                for(int i=0;i<movie_details.size();i++){
                    Log.d("movie_info", " movie_name: "+ movie_details.get(i).name + " movie_time: "+ movie_details.get(i).date+" seats available: "+movie_details.get(i).available+" seats filled: "+movie_details.get(i).filled);
                }

            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mname = "Yashoda";
                dbHelper.updateMovies(mname);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mname = "Yashoda";
                dbHelper.deleteMovies(mname);
            }
        });
    }
}
