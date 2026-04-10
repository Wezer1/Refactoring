package com.example.discountStrategy;

public class PercentFromItemSumDiscountStrategy implements DiscountStrategy {

    private final double percent;

    public PercentFromItemSumDiscountStrategy(double percent) {
        this.percent = percent;
    }

    @Override
    public double calculateDiscount(int quantity, double price, double itemSum, double billTotal) {
        return itemSum * percent / 100;
    }
}
