package com.example.discountStrategy;

import com.example.DTO.Customer;
import com.example.bonusStrategy.BonusStrategy;

public class QuantityConditionalBonusStrategy implements BonusStrategy {

    private final double percent;
    private final int minQuantityForUsage;

    public QuantityConditionalBonusStrategy(double percent, int minQuantityForUsage) {
        this.percent = percent;
        this.minQuantityForUsage = minQuantityForUsage;
    }

    @Override
    public int calculateBonus(int quantity, double price, double itemSum, double billTotal) {
        return (int) (itemSum * percent / 100);
    }

    @Override
    public double useBonus(int quantity, double sumAfterDiscount, Customer customer) {

        if (quantity > minQuantityForUsage) {
            return customer.useBonus((int) sumAfterDiscount);
        }

        return 0;
    }
}
