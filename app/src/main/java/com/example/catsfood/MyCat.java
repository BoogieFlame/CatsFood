package com.example.catsfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyCat extends AppCompatActivity {

    RecyclerView rv_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cat);

        rv_cat = findViewById(R.id.rv_cat);
    }

    public static class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {
        private final List<Cat> values;

        public CatAdapter(List<Cat> music) {
            values = music;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cat, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            holder.name.setText(values.get(position).name);
            holder.age.setText(values.get(position).age);
            holder.weight.setText(values.get(position).weight);
            holder.is_male.setText(values.get(position).is_male ? "Кот" : "Кошка");

            holder.itemView.setTag(values.get(position));
            holder.itemView.setOnClickListener(onClickListener);
        }

        @Override
        public int getItemCount() {
            return values.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView name;
            final TextView age;
            final TextView weight;
            final TextView is_male;

            ViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.cat_name);
                age = view.findViewById(R.id.age_cat);
                weight = view.findViewById(R.id.weight_cat);
                is_male = view.findViewById(R.id.sex_cat);
            }
        }
        final private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cat item = (Cat) view.getTag();
                Context context = view.getContext();
                Intent intent = new Intent(context, DetailCat.class);
                intent.putExtra("id", item.id);
                context.startActivity(intent);
            }
        };

    }
}