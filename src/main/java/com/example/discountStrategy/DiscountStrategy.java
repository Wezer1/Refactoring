package com.example.discountStrategy;

public interface DiscountStrategy {

    double calculateDiscount(int quantity,
                             double price,
                             double itemSum,
                             double billTotal);
}
