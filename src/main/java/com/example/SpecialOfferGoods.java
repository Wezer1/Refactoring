package com.example;

public class SpecialOfferGoods extends Goods {

    public SpecialOfferGoods(String title) {
        super(title);
    }

    @Override
    public double[] getBonus(int quantity, double price) {
        double itemSum = quantity * price;
        double discountAmount = 0;

        if (quantity > 10) {
            discountAmount = itemSum * 0.005;
        }

        return new double[]{discountAmount, 0};
    }

    @Override
    public double getUsedBonus(int quantity, double sumAfterDiscount, Customer customer) {
        if (quantity > 1) {
            return customer.useBonus((int) sumAfterDiscount);
        }
        return 0;
    }
}
