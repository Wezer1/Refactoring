package com.example.bonusStrategy;

import com.example.DTO.Customer;

public class NoBonusStrategy implements BonusStrategy {

    @Override
    public int calculateBonus(int quantity, double price, double itemSum, double billTotal) {
        return 0;
    }

    @Override
    public double useBonus(int quantity, double sumAfterDiscount, Customer customer) {
        return 0;
    }
}