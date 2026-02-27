package com.example.test;

import com.example.*;
import com.example.factory.BillFactory;
import com.example.formatFile.IFileSource;
import com.example.formatFile.YamlFileSource;
import com.example.generator.BillGenerator;
import com.example.view.HtmlView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProgramHtml {

    public static void main(String[] args) throws IOException {


        String filename = "BillInfo.yaml";

        if (args.length == 1) {
            filename = args[0];
        }

        // 🔹 1. Источник данных
        BufferedReader reader =
                new BufferedReader(new FileReader(filename));

        // 🔹 2. Парсер конкретного формата
        IFileSource file = new YamlFileSource();
        Bill bill = BillFactory.create(file, reader);

        BillGenerator generator =
                new BillGenerator(bill, new HtmlView());

        String html = generator.generate();

        System.out.println(generator.generate());
    }
}
