package com.example.discountStrategy;

public class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public double calculateDiscount(int quantity, double price, double itemSum, double billTotal) {
        return 0;
    }
}
