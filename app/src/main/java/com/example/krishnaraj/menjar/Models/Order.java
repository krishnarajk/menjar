package com.example.krishnaraj.menjar.Models;

import java.util.ArrayList;

/**
 * Created by krishnaraj on 7/5/17.
 */

public class Order
{
    public int tableId;
    public String comments;
    public float amount;
    public float amountTotal;
    public ArrayList<OrderItem> items = new ArrayList<>();
}
