package com.example.jittanan.yhinyhang.models;

public class Menu {

    private String name,num_yhin,num_yhang,category,ingredient,howto,image;

    public Menu(String name, String num_yhin, String num_yhang, String category, String ingredient, String howto, String image){
        this.name = name;
        this.num_yhin = num_yhin;
        this.num_yhang = num_yhang;
        this.category = category;
        this.ingredient = ingredient;
        this.howto = howto;
        this.image = image;

    }

    public String getName_menu() { return name;}

    public String getNum_yhin() { return num_yhin;}

    public String getNum_yhang(){ return num_yhang;}

    public String getCategory_menu() { return category;}

    public String getIngredient_menu() { return ingredient;}

    public String getHowto_food() { return  howto;}

    public String getImage_menu() { return image;}

}
