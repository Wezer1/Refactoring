package com.example.bonusStrategy;

import com.example.DTO.Customer;

public class PercentFromBillTotalBonusStrategy implements BonusStrategy {

    private final double percent;

    public PercentFromBillTotalBonusStrategy(double percent) {
        this.percent = percent;
    }

    @Override
    public int calculateBonus(int quantity, double price, double itemSum, double billTotal) {
        return (int) (billTotal * percent / 100);
    }

    @Override
    public double useBonus(int quantity, double sumAfterDiscount, Customer customer) {
        return customer.getBonus();
    }
}