package com.example.finechefs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finechefs.Adapter.LunchAdapter;
import com.example.finechefs.Adapter.SnackAdapter;
import com.example.finechefs.Model.RecipeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SnackActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    private ArrayList<RecipeModel> sNameModels = new ArrayList<>();
    private String TAG="SnacActivity";
    private RecyclerView recyclerView;
    private SnackAdapter snackAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);
        recyclerView=findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        snackAdapter=new SnackAdapter(sNameModels,SnackActivity.this);
        recyclerView.setAdapter(snackAdapter);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        Query query=myRef.child("Recipes").child("snacks");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    RecipeModel recipe = snapshot1.getValue(RecipeModel.class);
                    sNameModels.add(recipe);
                }
                snackAdapter.notifyDataSetChanged();
                // dataLoadListener.OnNameLoaded();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}