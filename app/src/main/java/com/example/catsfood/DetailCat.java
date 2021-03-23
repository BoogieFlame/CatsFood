package com.example.catsfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class DetailCat extends AppCompatActivity {

    Button update_create;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch sw_is_male;
    EditText inp_name;
    EditText inp_age;
    EditText inp_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cat);

        update_create = findViewById(R.id.update);
        sw_is_male = findViewById(R.id.sw_sex);
        inp_name = findViewById(R.id.inp_name);
        inp_age = findViewById(R.id.inp_age);
        inp_weight = findViewById(R.id.inp_weight);

        Intent intent = getIntent();
        String s = intent.getStringExtra("create");
        if (s.equals("CREATE"))
        {
            update_create.setText("Создать");
            update_create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // создать
                }
            });
        }
        else
        {
            update_create.setText("Обновить");
            update_create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // обновить
                }
            });
        }
    }
}