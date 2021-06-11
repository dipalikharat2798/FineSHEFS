package com.example.finechefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finechefs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(new Intent(MainActivity.this,BreakfastActivity.class));
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Breakfast Recipes",Toast.LENGTH_SHORT).show();
            }
        });
        binding.card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(new Intent(MainActivity.this,LunchActivity.class));
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Lunch Recipes",Toast.LENGTH_SHORT).show();
            }
        });
        binding.card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(new Intent(MainActivity.this,SnackActivity.class));
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Snacks Recipes",Toast.LENGTH_SHORT).show();
            }
        });
        binding.card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(new Intent(MainActivity.this,DinnerActivity.class));
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Dinner Recipes",Toast.LENGTH_SHORT).show();
            }
        });
    }
}