package com.example.DTO;

import com.example.bonusStrategy.BonusStrategy;
import com.example.discountStrategy.DiscountStrategy;

public class Goods {

    protected String title;

    private BonusStrategy bonusStrategy;
    private DiscountStrategy discountStrategy;

    public Goods(String title,
                 BonusStrategy bonusStrategy,
                 DiscountStrategy discountStrategy) {

        this.title = title;
        this.bonusStrategy = bonusStrategy;
        this.discountStrategy = discountStrategy;
    }

    public String getTitle() {
        return title;
    }

    public BonusStrategy getBonusStrategy() {
        return bonusStrategy;
    }

    public DiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }
}
