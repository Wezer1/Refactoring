package com.example;

public class RegularGoods extends Goods {

    public RegularGoods(String title) {
        super(title);
    }

    @Override
    public double[] getBonus(int quantity, double price) {
        double itemSum = quantity * price;
        double discountAmount = 0;

        if (quantity > 2) {
            discountAmount = itemSum * 0.03;
        }

        int bonusEarned = (int) (itemSum * 0.05);

        return new double[]{discountAmount, bonusEarned};
    }

    @Override
    public double getUsedBonus(int quantity, double sumAfterDiscount, Customer customer) {
        if (quantity > 5) {
            return customer.useBonus((int) sumAfterDiscount);
        }
        return 0;
    }
}
