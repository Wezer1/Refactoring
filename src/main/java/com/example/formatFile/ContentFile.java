package com.example.formatFile;

import com.example.DTO.Customer;
import com.example.DTO.Goods;
import com.example.DTO.Item;

import java.io.BufferedReader;
import java.io.IOException;

public interface ContentFile {

    void setSource(BufferedReader reader) throws IOException;

    Customer getCustomer() throws IOException;

    int getGoodsCount() throws IOException;

    Goods getNextGood() throws IOException;

    int getItemsCount() throws IOException;

    Item getNextItem(Goods[] goods) throws IOException;
}