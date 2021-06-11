package com.example.finechefs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finechefs.Adapter.DinnerAdapter;
import com.example.finechefs.Adapter.SnackAdapter;
import com.example.finechefs.Model.RecipeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DinnerActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    private ArrayList<RecipeModel> dNameModels = new ArrayList<>();
    private String TAG = "DinnerActivity";
    private RecyclerView recyclerView;
    private DinnerAdapter dinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dinnerAdapter = new DinnerAdapter(dNameModels, DinnerActivity.this);
        recyclerView.setAdapter(dinnerAdapter);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        Query query = myRef.child("Recipes").child("Dinner");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    RecipeModel recipe = snapshot1.getValue(RecipeModel.class);
                    dNameModels.add(recipe);
                }
                dinnerAdapter.notifyDataSetChanged();
                // dataLoadListener.OnNameLoaded();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}