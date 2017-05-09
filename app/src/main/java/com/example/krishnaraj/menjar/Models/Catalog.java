package com.example.krishnaraj.menjar.Models;

import java.io.Serializable;

/**
 * Created by krishnaraj on 28/3/17.
 */

public class Catalog implements Serializable{
    public int id;
    public String name;
    public int price;
    public String category;
    public String subCategory;
    public String description;
    public int availability;
    public String image;

    @Override
    public String toString(){return id+" "+name+" ";}
    public String getName(){return name;}
    public int getPrice(){return price;}
    public String getImage(){return image;}
}
