package com.example;

public class SaleGoods extends Goods {

    public SaleGoods(String title) {
        super(title);
    }

    @Override
    public double[] getBonus(int quantity, double price) {
        double itemSum = quantity * price;
        double discountAmount = 0;

        if (quantity > 3) {
            discountAmount = itemSum * 0.01;
        }

        int bonusEarned = (int) (itemSum * 0.01);

        return new double[]{discountAmount, bonusEarned};
    }

    @Override
    public double getUsedBonus(int quantity, double sumAfterDiscount, Customer customer) {
        return 0; // SALE не использует бонусы
    }
}
