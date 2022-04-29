package models;

public class Pokemon {
    //You do NOT need to change anything here. You CAN ADD fields here if you want to load more
    //Pokemon data. Do not remove data from here. It's all needed. :) See the Zoom session.
    private int id;
    private int baseExperience;
    private int height, weight;


    private String name;
    private String url;
    private String abilities;

    public void setBaseExperience(int exp) {
        this.baseExperience = exp;
    }
    public int getBaseExperience() {
        return this.baseExperience;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getAbilities() {return abilities;}
    public void setAbilities(String a) {this.abilities = a;}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setHeight(int height) {this.height = height;}
    public int getHeight() {return height;}

    public void setWeight(int weight) {this.weight = weight;}
    public int getWeight() {return weight;}

    @Override
    public String toString() {
        return String.format("Pokemon %s: %s , BE: %s, H: %s, W: %s, URL: %s", id, name, baseExperience, height, weight, url);
    }
}