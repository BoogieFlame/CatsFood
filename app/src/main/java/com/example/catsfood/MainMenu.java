package com.example.catsfood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    Button create_cat;
    Button food_cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        create_cat = findViewById(R.id.go_my_cats);
        create_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, DetailCat.class);
                intent.putExtra("create", "CREATE");
                context.startActivity(intent);
            }
        });
        food_cat = findViewById(R.id.go_foods);
        food_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, MyCatFood.class);
                context.startActivity(intent);
            }
        });
    }
}
