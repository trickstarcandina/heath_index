package com.example.chatbotspring.utils;

import com.example.chatbotspring.services.fuzzylogic.model.DuLieuFuzzy;
import com.example.chatbotspring.services.fuzzylogic.model.DuLieuFuzzyConvert;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FileReaderCSV {

    private static String SPLIT_BY = ",";

    private static NumberFormat format = NumberFormat.getInstance(Locale.getDefault());

    private static final Logger logger = LoggerFactory.getLogger(FileReaderCSV.class);

    public static List<DuLieuFuzzy> readFileFuzzy(String excelFilePath) {
        try {
            List<DuLieuFuzzyConvert> listAtomic =
                    new CsvToBeanBuilder(new FileReader(excelFilePath))
                            .withType(DuLieuFuzzyConvert.class)
                            .build()
                            .parse();

            List<DuLieuFuzzy> list = new ArrayList<>();

            listAtomic.forEach(e -> {
                list.add(
                        DuLieuFuzzy
                                .builder()
                                .gioiTinh(e.getGioiTinh())
                                .tuoi(convertStringToInteger(e.getTuoi()))
                                .chieuCaoMin(convertStringToDouble(e.getChieuCaoMin()))
                                .chieuCaoMax(convertStringToDouble(e.getChieuCaoMax()))
                                .canNangMin(convertStringToDouble(e.getCanNangMin()))
                                .canNangMax(convertStringToDouble(e.getCanNangMax()))
                                .duongHuyetMin(convertStringToDouble(e.getDuongHuyetMin()))
                                .duongHuyetMax(convertStringToDouble(e.getDuongHuyetMax()))
                                .nhipTimMin(convertStringToInteger(e.getNhipTimMin()))
                                .nhipTimMax(convertStringToInteger(e.getNhipTimMax()))
                                .cholesterolMin(convertStringToDouble(e.getCholesterolMin()))
                                .cholesterolMax(convertStringToDouble(e.getCholesterolMax()))
                                .build()
                );
            });

            return list;
        } catch (Exception e) {
            logger.error("error when read file csv {}", e.getMessage());
        }
        return new ArrayList<>();
    }

    private static double convertStringToDouble(String s) {
        try {
            return format.parse(s.replace(",", ".")).doubleValue();
        } catch (Exception e) {
            logger.error("error when parse file csv {}", e.getMessage());
        }
        return -1.0;
    }

    private static int convertStringToInteger(String s) {
        try {
            return format.parse(s).intValue();
        } catch (Exception e) {
            logger.error("error when parse file csv {}", e.getMessage());
        }
        return -1;
    }

//    public static void main(String[] args) {
//        readFileFuzzy("src/main/resources/fuzzy.csv").forEach(System.out::println);
//    }



}
