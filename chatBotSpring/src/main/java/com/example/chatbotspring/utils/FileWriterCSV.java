package com.example.chatbotspring.utils;

import com.example.chatbotspring.services.neuralnetwork.model.DuLieuTraining;
import com.opencsv.CSVWriter;
import com.opencsv.ResultSetColumnNameHelperService;
import lombok.experimental.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

public class FileWriterCSV {

    private static final Logger logger = LoggerFactory.getLogger(FileWriterCSV.class);

    public static void writeFileTraining(List<DuLieuTraining> trainingList, String path) {
        List<String[]> csvData = trainingList.stream().map(e ->
                new String[]{
                        e.getGioiTinh(),
                        String.valueOf(e.getTuoi()),
                        String.valueOf(e.getChieuCao()),
                        String.valueOf(e.getCanNang()),
                        String.valueOf(e.getDuongHuyet()),
                        String.valueOf(e.getNhipTim()),
                        String.valueOf(e.getCholesterol()),
                        String.valueOf(e.getChiSoSucKhoe())
                }).collect(Collectors.toList());

        csvData.add(0, new String[]{
                "gioi tinh", "do tuoi", "chieu cao", "can nang",
                "chi so duong huyet", "nhip tim", "cholesterol", "chi so suc khoe"
        });

        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            writer.writeAll(csvData);
        } catch (Exception e) {
            logger.error("error when write csv");
        }
    }

}
