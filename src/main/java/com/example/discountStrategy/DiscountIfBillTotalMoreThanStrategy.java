package com.example.discountStrategy;

public class DiscountIfBillTotalMoreThanStrategy implements DiscountStrategy {

    private final double threshold;
    private final double percent;

    public DiscountIfBillTotalMoreThanStrategy(double threshold, double percent) {
        this.threshold = threshold;
        this.percent = percent;
    }

    @Override
    public double calculateDiscount(int quantity, double price, double itemSum, double billTotal) {

        if (billTotal >= threshold) {
            return itemSum * percent / 100;
        }

        return 0;
    }
}
