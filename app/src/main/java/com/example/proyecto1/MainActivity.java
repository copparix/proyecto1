package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;



public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void goLogin(View view){
        Intent intent = new Intent(MainActivity.this, loginActivity.class);
        startActivity(intent);
    }

    public void goRegister(View view){
        Intent intent = new Intent(MainActivity.this, registerActivity.class);
        startActivity(intent);
    }




}