package com.example.discountStrategy;

public class QuantityThresholdPercentDiscountStrategy implements DiscountStrategy {

    private final int minQuantity;
    private final double percent;

    public QuantityThresholdPercentDiscountStrategy(int minQuantity, double percent) {
        this.minQuantity = minQuantity;
        this.percent = percent;
    }

    @Override
    public double calculateDiscount(int quantity,
                                    double price,
                                    double itemSum,
                                    double billTotal) {

        if (quantity > minQuantity) {
            return itemSum * percent / 100;
        }

        return 0;
    }
}
