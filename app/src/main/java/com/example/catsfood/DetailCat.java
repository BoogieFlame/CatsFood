package com.example.catsfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

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
                    try {
                        Cat cat = new Cat(Data.mycat.size(),inp_name.getText() + "", Long.parseLong(inp_age.getText() + ""), Long.parseLong(inp_weight.getText() + ""), sw_is_male.isChecked());
                        Data.update(cat);
                        Data.mycat.add(cat);
                        Toast.makeText(getApplicationContext(), "Кот создан", Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e)
                    {
                        Cat cat = new Cat(Data.mycat.size(),inp_name.getText() + "", Long.parseLong("0"), Long.parseLong("0"), sw_is_male.isChecked());
                        Data.update(cat);
                        Data.mycat.add(cat);
                        Toast.makeText(getApplicationContext(), "Кот создан", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
        else
        {
            int id = intent.getIntExtra("id", 0);
            Cat cat = Data.mycat.get(id);
            sw_is_male.setChecked(cat.is_male);
            inp_name.setText(cat.name);
            inp_age.setText(String.valueOf(cat.age));
            inp_weight.setText(String.valueOf(cat.weight));
            update_create.setText("Обновить");
            update_create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Cat cat = new Cat(id,inp_name.getText() + "", Long.parseLong(inp_age.getText() + ""), Long.parseLong(inp_weight.getText() + ""), sw_is_male.isChecked());
                        Data.mycat.set(id, cat);
                        Data.update(null);
                        MyCatFood.rv_cat.setAdapter(new MyCatFood.CatAdapter(Data.mycat));
                        Toast.makeText(getApplicationContext(), "Данные кота обновлены", Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e)
                    {
                        Cat cat = new Cat(Data.mycat.size(),inp_name.getText() + "", Long.parseLong("0"), Long.parseLong("0"), sw_is_male.isChecked());
                        Data.mycat.set(id, cat);
                        Data.update(null);
                        MyCatFood.rv_cat.setAdapter(new MyCatFood.CatAdapter(Data.mycat));
                        Toast.makeText(getApplicationContext(), "Данные кота обновлены", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}