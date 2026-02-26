package com.example.factory;

import com.example.Bill;
import com.example.DTO.Customer;
import com.example.DTO.Goods;
import com.example.formatFile.ContentFile;

import java.io.BufferedReader;
import java.io.IOException;

public class BillFactory {

    public static Bill create(ContentFile contentFile,
                              BufferedReader reader)
            throws IOException {

        contentFile.setSource(reader);

        Customer customer = contentFile.getCustomer();
        Bill bill = new Bill(customer);

        int goodsCount = contentFile.getGoodsCount();
        Goods[] goods = new Goods[goodsCount];

        for (int i = 0; i < goodsCount; i++)
            goods[i] = contentFile.getNextGood();

        int itemsCount = contentFile.getItemsCount();

        for (int i = 0; i < itemsCount; i++)
            bill.addGoods(contentFile.getNextItem(goods));

        return bill;
    }
}
