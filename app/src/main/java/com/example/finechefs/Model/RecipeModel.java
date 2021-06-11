package com.example.finechefs.Model;

public class RecipeModel {
    private String name;
    private String description;
    private String duration;
    private String image;
    private String steps;

    public RecipeModel(){

    }
    public RecipeModel(String name, String description, String duration, String image, String steps) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.image = image;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
