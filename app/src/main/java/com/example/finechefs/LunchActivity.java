package com.example.finechefs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finechefs.Adapter.LunchAdapter;
import com.example.finechefs.Adapter.RecipeAdapter;
import com.example.finechefs.Model.RecipeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LunchActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    private ArrayList<RecipeModel> lNameModels = new ArrayList<>();
    private String TAG="LunchActivity";
    private RecyclerView recyclerView;
    private LunchAdapter lunchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        recyclerView=findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lunchAdapter=new LunchAdapter(lNameModels,LunchActivity.this);
        recyclerView.setAdapter(lunchAdapter);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        Query query=myRef.child("Recipes").child("Lunch");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    RecipeModel recipe = snapshot1.getValue(RecipeModel.class);
                    lNameModels.add(recipe);
                }
                lunchAdapter.notifyDataSetChanged();
                // dataLoadListener.OnNameLoaded();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}