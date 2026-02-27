package com.example.test;

import com.example.*;
import com.example.factory.BillFactory;
import com.example.formatFile.IFileSource;
import com.example.formatFile.YamlFileSource;
import com.example.generator.BillGenerator;
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

        IFileSource file = new YamlFileSource();

        Bill bill = BillFactory.create(file, reader);

        BillGenerator generator =
                new BillGenerator(bill, new TxtView());

        System.out.println(generator.generate());
    }
}