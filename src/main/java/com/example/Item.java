package com.example;

public class Item {

    private Goods goods;
    private int quantity;
    private double price;

    public Item(Goods goods, int quantity, double price) {
        this.goods = goods;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Goods getGoods() {
        return goods;
    }

//    public double[] getBonus() {
//        double itemSum = this.quantity * this.price;
//        return this.goods.getBonus(itemSum, this.quantity);
//    }

}

