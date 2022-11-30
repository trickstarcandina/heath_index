package com.example.chatbotspring.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.chatbotspring.services.fuzzylogic.model.DuLieu;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReaderCSV {

    public static final int COLUMN_GIOI_TINH = 0;
    public static final int COLUMN_TUOI = 1;
    public static final int COLUMN_CHIEU_CAO_MIN = 2;
    public static final int COLUMN_CHIEU_CAO_MAX = 3;
    public static final int COLUMN_CAN_NANG_MIN = 4;
    public static final int COLUMN_CAN_NANG_MAX = 5;
    public static final int COLUMN_DUONG_HUYET_MIN = 6;
    public static final int COLUMN_DUONG_HUYET_MAX = 7;
    public static final int COLUMN_NHIP_TIM_MIN = 8;
    public static final int COLUMN_NHIP_TIM_MAX = 9;
    public static final int COLUMN_CHOLESTEROL_MIN = 10;
    public static final int COLUMN_CHOLESTEROL_MAX = 11;
    private static final Logger logger = LoggerFactory.getLogger(FileReaderCSV.class);

    public static List<DuLieu> read(String excelFilePath) {
        try {
            List<DuLieu> list = new ArrayList<>();
            InputStream inputStream = Files.newInputStream(new File(excelFilePath).toPath());
            Workbook workbook = getWorkbook(inputStream, excelFilePath);
            Sheet sheet = workbook.getSheetAt(0);
            // Get all rows
            for (Row nextRow : sheet) {
                if (nextRow.getRowNum() == 0) {
                    // Ignore header
                    continue;
                }

                // Get all cells
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                // Read cells and set value for book object
                DuLieu duLieu = new DuLieu();
                int d = 0;
                while (cellIterator.hasNext()) {
                    d++;
                    //Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                    // Set value for book object
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case COLUMN_GIOI_TINH:
                            duLieu.setGioiTinh((String) getCellValue(cell));
                            break;
                        case COLUMN_TUOI:
                            duLieu.setTuoi(BigDecimal.valueOf((double) cellValue).intValue());
                            break;
                        case COLUMN_CHIEU_CAO_MIN:
                            String x = (String) getCellValue(cell); // chuyen . thanh ,
                            duLieu.setChieuCaoMin(Double.parseDouble(convertToString(x)));
                            //System.out.println(duLieu.getChieuCaoMin());
                            break;
                        case COLUMN_CHIEU_CAO_MAX:
                            String x1 = (String) getCellValue(cell); // chuyen . thanh ,
                            duLieu.setChieuCaoMax(Double.parseDouble(convertToString(x1)));
                            break;
                        case COLUMN_CAN_NANG_MIN:
                            String x2 = (String) getCellValue(cell); // chuyen . thanh ,
                            duLieu.setCanNangMin(Double.parseDouble(convertToString(x2)));
                            break;
                        case COLUMN_CAN_NANG_MAX:
                            String x3 = (String) getCellValue(cell); // chuyen . thanh ,
                            duLieu.setCanNangMax(Double.parseDouble(convertToString(x3)));
                            break;
                        case COLUMN_DUONG_HUYET_MIN:
                            String x4 = (String) getCellValue(cell); // chuyen . thanh ,
                            duLieu.setDuongHuyetMin(Double.parseDouble(convertToString(x4)));
                            break;
                        case COLUMN_DUONG_HUYET_MAX:
                            String x5 = (String) getCellValue(cell); // chuyen . thanh ,
                            duLieu.setDuongHuyetMax(Double.parseDouble(convertToString(x5)));
                            break;
                        case COLUMN_NHIP_TIM_MIN:
                            duLieu.setNhipTimMin(BigDecimal.valueOf((double) cellValue).intValue());
                            break;
                        case COLUMN_NHIP_TIM_MAX:
                            duLieu.setNhipTimMax(BigDecimal.valueOf((double) cellValue).intValue());
                            break;
                        case COLUMN_CHOLESTEROL_MIN:
                            String x6 = (String) getCellValue(cell); // chuyen . thanh ,
                            duLieu.setCholesterolMin(Double.parseDouble(convertToString(x6)));
                            break;
                        case COLUMN_CHOLESTEROL_MAX:
                            String x7 = (String) getCellValue(cell); // chuyen . thanh ,
                            duLieu.setCholesterolMax(Double.parseDouble(convertToString(x7)));
                            break;
                        default:
                            break;
                    }
                }
                list.add(duLieu);
            }

            workbook.close();
            inputStream.close();

            List<DuLieu> out = new ArrayList<>();
            for (int i = 0; i <= 100; i++) // lay 100 dong
                out.add(list.get(i));

            return out;
        } catch (Exception e) {
            logger.error("exception when read csv {}", e.getMessage() );
        }
        return new ArrayList<>();
    }

    public static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }


    public static String convertToString(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == ',') res.append('.');
            else res.append(s.charAt(i));
        return res.toString();
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }

//    public static void main(String[] args) throws IOException {
//        String excelFilePath = "E:\\CODE\\Intellij\\Fuzzy\\src\\main\\java\\fuzzy\\input.xlsx";
//        System.out.println(read(excelFilePath).size());
//        List<DuLieu> list = read(excelFilePath);
//        for (int i = 0; i < list.size(); i++)
//            System.out.println(i + " " + list.get(i).toString());
//    }
}
