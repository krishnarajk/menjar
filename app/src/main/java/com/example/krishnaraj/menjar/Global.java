package com.example.krishnaraj.menjar;

import android.app.Application;

import com.example.krishnaraj.menjar.Models.Order;
import com.example.krishnaraj.menjar.Models.OrderItem;

import java.util.ArrayList;

/**
 * Created by krishnaraj on 28/3/17.
 */

public class Global extends Application {
    public static final String BASE_URL = "http://192.168.43.155:3000/";
    public static Order order = new Order();
    public static int tableId;
    public static ArrayList<OrderItem> orderItemsGlobal = new ArrayList<>();
    public static float amount;
    public static int orderId;
}

