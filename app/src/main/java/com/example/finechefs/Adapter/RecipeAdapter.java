package com.example.finechefs.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finechefs.Break_Des_Activity;
import com.example.finechefs.BreakfastActivity;
import com.example.finechefs.Model.RecipeModel;
import com.example.finechefs.R;
import com.example.finechefs.StartActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

public class RecipeAdapter extends  RecyclerView.Adapter<RecipeAdapter.ViewHolder> implements Filterable {
BreakfastActivity activity;
    ArrayList<RecipeModel> recipeModels;
    ArrayList<RecipeModel> recipeModelsall;

    public RecipeAdapter(ArrayList<RecipeModel> recipeModels, BreakfastActivity activity) {

        this.recipeModels=recipeModels;
        this.activity=activity;
        this.recipeModelsall=new ArrayList<>(recipeModels);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breakfast_item,parent,false);
        return new ViewHolder(view);
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

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<RecipeModel> filterlist = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filterlist.addAll(recipeModelsall);
            }else {
                for(RecipeModel recipe : recipeModelsall){
                    String recipename=recipe.getName();
                    if(recipename.toLowerCase().contains(constraint.toString().toLowerCase())){
                        filterlist.add(recipe);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filterlist;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            recipeModels.clear();
            recipeModels.addAll((Collection<? extends RecipeModel>) results.values);
            notifyDataSetChanged();
        }
    };
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,duration,description,steps;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            duration=itemView.findViewById(R.id.duration);
            description=itemView.findViewById(R.id.description);
            imageView=itemView.findViewById(R.id.image_id);
           // steps=itemView.findViewById(R.id.steps);

        }
    }
}
