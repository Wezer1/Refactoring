package com.example.generator;

import com.example.Bill;
import com.example.DTO.BillSummary;
import com.example.DTO.ItemSummary;
import com.example.view.IView;

public class BillGenerator {

    private Bill bill;
    private IView view;

    public BillGenerator(Bill bill, IView view) {
        this.bill = bill;
        this.view = view;
    }

    public String generate() {

        BillSummary summary = bill.process();

        StringBuilder result = view.getHeader(bill.getCustomer());

        for (ItemSummary itemSummary : summary.getItems()) {

            result.append(
                    view.getItemString(
                            itemSummary.getItem(),
                            itemSummary.getItemSum(),
                            itemSummary.getDiscountAmount(),
                            itemSummary.getFinalAmount(),
                            itemSummary.getBonusEarned()
                    )
            );
        }

        result.append(
                view.getFooter(
                        summary.getTotalAmount(),
                        summary.getTotalBonus()
                )
        );

        return result.toString();
    }
}