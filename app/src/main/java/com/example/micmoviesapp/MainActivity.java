package com.example.micmoviesapp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText enq_user, enq_pass;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enq_user=(EditText)findViewById(R.id.enq_user);
        enq_pass=(EditText)findViewById(R.id.enq_pass);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=enq_user.getText().toString();
                String password=enq_pass.getText().toString();
                if(username.equals("Susheel") && password.equals("1710"))
                {
                    Intent inext=new Intent(MainActivity.this, UpdateInfo.class);
                    inext.putExtra("username",username);
                    startActivity(inext);
                    finish();
                }
                else
                {
                    Intent inext=new Intent(MainActivity.this, CheckActivity.class);
                    inext.putExtra("username", username);
                    startActivity(inext);
                    finish();
                }
            }
        });


    }
}
