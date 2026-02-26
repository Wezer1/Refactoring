package com.example.test;

import com.example.*;
import com.example.DTO.Customer;
import com.example.DTO.Goods;
import com.example.DTO.Item;
import com.example.factory.BillFactory;
import com.example.formatFile.ContentFile;
import com.example.formatFile.YamlContentFile;
import com.example.generator.BillGenerator;
import com.example.goods.RegularGoods;
import com.example.goods.SaleGoods;
import com.example.goods.SpecialOfferGoods;
import com.example.view.IView;
import com.example.view.TxtView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Program {

    public static void main(String[] args) throws IOException {

        String filename = "BillInfo.yaml";

        if (args.length == 1) {
            filename = args[0];
        }

        BufferedReader reader =
                new BufferedReader(new FileReader(filename));

        ContentFile file = new YamlContentFile();

        Bill bill = BillFactory.create(file, reader);

        BillGenerator generator =
                new BillGenerator(bill, new TxtView());

        System.out.println(generator.generate());
    }
}