package com.example.DTO;

import com.example.Item;

public class ItemSummary {

    private Item item;
    private double itemSum;
    private double discountAmount;
    private double usedBonus;
    private double finalAmount;
    private int bonusEarned;

    public ItemSummary(Item item,
                       double itemSum,
                       double discountAmount,
                       double usedBonus,
                       double finalAmount,
                       int bonusEarned) {
        this.item = item;
        this.itemSum = itemSum;
        this.discountAmount = discountAmount;
        this.usedBonus = usedBonus;
        this.finalAmount = finalAmount;
        this.bonusEarned = bonusEarned;
    }

    public Item getItem() { return item; }
    public double getItemSum() { return itemSum; }
    public double getDiscountAmount() { return discountAmount; }
    public double getUsedBonus() { return usedBonus; }
    public double getFinalAmount() { return finalAmount; }
    public int getBonusEarned() { return bonusEarned; }
}
