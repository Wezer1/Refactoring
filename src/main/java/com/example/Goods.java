package com.example;

public class Goods {

    protected String title;

    public Goods(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public double[] getBonus(int quantity, double price) {
        return new double[]{0, 0};
    }

    public double getUsedBonus(int quantity, double sumAfterDiscount, Customer customer) {
        return 0;
    }
}
