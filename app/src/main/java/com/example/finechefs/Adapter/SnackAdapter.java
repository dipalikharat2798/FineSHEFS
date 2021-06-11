package com.example.finechefs.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finechefs.Break_Des_Activity;
import com.example.finechefs.LunchActivity;
import com.example.finechefs.Model.RecipeModel;
import com.example.finechefs.R;
import com.example.finechefs.SnackActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SnackAdapter extends  RecyclerView.Adapter<SnackAdapter.ViewHolder> {
    SnackActivity activity;
    ArrayList<RecipeModel> recipeModels;

    public SnackAdapter(ArrayList<RecipeModel> recipeModels, SnackActivity activity) {

        this.recipeModels=recipeModels;
        this.activity=activity;
    }


    @NonNull
    @Override
    public SnackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breakfast_item,parent,false);
        return new SnackAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeModel recipeModel1=recipeModels.get(position);
        holder.itemView.setTag(recipeModels.get(position));
        holder.name.setText(recipeModels.get(position).getName());
        holder.duration.setText(recipeModels.get(position).getDuration());
        holder.description.setText(recipeModels.get(position).getDescription());
        String Url=recipeModels.get(position).getImage();
        Picasso.get().load(Url).into(holder.imageView);
        String step=recipeModels.get(position).getSteps();

        //   holder.steps.setText(recipeModels.get(position).getSteps());
//        Toast.makeText(activity,"step",Toast.LENGTH_SHORT).show();
        // Log.d("TAG", "onBindViewHolder: "+step);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, Break_Des_Activity.class);
                intent.putExtra("name",recipeModel1.getName());
                intent.putExtra("steps",recipeModel1.getSteps());
                intent.putExtra("image",recipeModel1.getImage());
                activity.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return recipeModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,duration,description;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            duration=itemView.findViewById(R.id.duration);
            description=itemView.findViewById(R.id.description);
            imageView=itemView.findViewById(R.id.image_id);
        }
    }
}
