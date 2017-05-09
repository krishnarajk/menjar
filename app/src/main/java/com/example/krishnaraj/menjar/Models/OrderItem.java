package com.example.krishnaraj.menjar.Models;

/**
 * Created by krishnaraj on 7/5/17.
 */
public class OrderItem {
    public int id;
    public int quantity;
    public String name;
    public float price;
    public OrderItem(int id,int quantity,String name,float price){
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }
}
