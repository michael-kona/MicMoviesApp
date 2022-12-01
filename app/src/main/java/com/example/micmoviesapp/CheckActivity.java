package com.example.micmoviesapp;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckActivity extends AppCompatActivity {
    Button btn;
    TextView tname,tdate,ttime,tavailable,tfilled;
    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_check);
        ListView listview = findViewById(R.id.list);
        //btn = (Button) findViewById(R.id.btn);
        DbHelper db = new DbHelper(getApplicationContext());
        ArrayList<String> a = db.movieNames();
        for(int i=0;i<a.size();i++) {
            Log.d("movie_name:",a.get(i));
        }
        ArrayList<HashMap<String,String>> al = new ArrayList<>();
        for(int i=0;i<a.size();i++) {
            HashMap<String, String> movie = new HashMap<>();
            movie.put("movie_no","movie"+i);
            movie.put("name",a.get(i));
            al.add(movie);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(CheckActivity.this, al, R.layout.list, new String[]{"name"}, new int[]{R.id.movie1});

        listview.setAdapter(simpleAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = a.get(i);
                DbHelper db = new DbHelper(getApplicationContext());
                Movies m = db.getDetails(s);
                tname = (TextView) findViewById(R.id.name);
                tdate = (TextView) findViewById(R.id.date);
                ttime = (TextView) findViewById(R.id.time);
                tavailable = (TextView) findViewById(R.id.available);
                tfilled = (TextView) findViewById(R.id.filled);
                tname.setText((m.name.toString()));
                tdate.setText((m.date.toString()));
                ttime.setText((m.time.toString()));
                int f = m.filled,a = m.available;
                tfilled.setText(String.valueOf(f));
                tavailable.setText(String.valueOf(a));
            }
        });




    }
}

