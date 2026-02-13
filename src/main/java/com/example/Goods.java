package com.example;

public class Goods {

    public static final int REGULAR = 0;
    public static final int SALE = 1;
    public static final int SPECIAL_OFFER = 2;

    private String title;
    private int priceCode;

    public Goods(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    public double[] getBonus(int quantity, double price) {
        double itemSum = quantity * price;
        double discountAmount = 0;
        int bonusEarned = 0;

        switch (priceCode) {
            case REGULAR:
                if (quantity > 2) discountAmount = itemSum * 0.03;
                bonusEarned = (int) (itemSum * 0.05);
                break;
            case SPECIAL_OFFER:
                if (quantity > 10) discountAmount = itemSum * 0.005;
                break;
            case SALE:
                if (quantity > 3) discountAmount = itemSum * 0.01;
                bonusEarned = (int) (itemSum * 0.01);
                break;
        }

        return new double[]{discountAmount, bonusEarned};
    }
}

