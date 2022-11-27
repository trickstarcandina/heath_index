package fuzzy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputController {

    public static final int COLUMN_gioitinh         = 0;
    public static final int COLUMN_tuoi             = 1;
    public static final int COLUMN_chieucao_min     = 2;
    public static final int COLUMN_chieucao_max     = 3;
    public static final int COLUMN_cannang_min      = 4;
    public static final int COLUMN_cannang_max      = 5;
    public static final int COLUMN_duonghuyet_min   = 6;
    public static final int COLUMN_duonghuyet_max   = 7;
    public static final int COLUMN_nhiptim_min      = 8;
    public static final int COLUMN_nhiptim_max      = 9;
    public static final int COLUMN_cholesterol_min  = 10;
    public static final int COLUMN_cholesterol_max  = 11;


    public static List<DuLieu> read(String excelFilePath) throws IOException {
        List<DuLieu>list = new ArrayList<>();
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = getWorkbook(inputStream, excelFilePath);
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            DuLieu duLieu = new DuLieu();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_gioitinh:
                        duLieu.setGioitinh((String) getCellValue(cell));
                        break;
                    case COLUMN_tuoi:
                        duLieu.setTuoi(new BigDecimal((double) cellValue).intValue());
                        break;
                    case COLUMN_chieucao_min:
                        String x = (String) getCellValue(cell); // chuyen . thanh ,
                        duLieu.setChieucaomin(Double.parseDouble(converst(x)));
                        break;
                    case COLUMN_chieucao_max:
                        String x1 = (String) getCellValue(cell); // chuyen . thanh ,
                        duLieu.setChieucaomax(Double.parseDouble(converst(x1)));
                        break;
                    case COLUMN_cannang_min:
                        String x2 = (String) getCellValue(cell); // chuyen . thanh ,
                        duLieu.setCannangmin(Double.parseDouble(converst(x2)));
                        break;
                    case COLUMN_cannang_max:
                        String x3 = (String) getCellValue(cell); // chuyen . thanh ,
                        duLieu.setCannangmax(Double.parseDouble(converst(x3)));
                        break;
                    case COLUMN_duonghuyet_min:
                        String x4 = (String) getCellValue(cell); // chuyen . thanh ,
                        duLieu.setDuonghuyetmin(Double.parseDouble(converst(x4)));
                        break;
                    case COLUMN_duonghuyet_max:
                        String x5 = (String) getCellValue(cell); // chuyen . thanh ,
                        duLieu.setDuonghuyetmax(Double.parseDouble(converst(x5)));
                        break;
                    case COLUMN_nhiptim_min:
                        duLieu.setNhiptimmin(new BigDecimal((double) cellValue).intValue());
                        break;
                    case COLUMN_nhiptim_max:
                        duLieu.setNhiptimmax(new BigDecimal((double) cellValue).intValue());
                        break;
                    case COLUMN_cholesterol_min:
                        String x6 = (String) getCellValue(cell); // chuyen . thanh ,
                        duLieu.setCholesteromin(Double.parseDouble(converst(x6)));
                        break;
                    case COLUMN_cholesterol_max:
                        String x7 = (String) getCellValue(cell); // chuyen . thanh ,
                        duLieu.setCholesteromax(Double.parseDouble(converst(x7)));
                        break;
                    default:
                        break;
                }
            }
            list.add(duLieu);
        }

        workbook.close();
        inputStream.close();

        List<DuLieu>out = new ArrayList<>();
        for(int i = 0; i <= 100; i++) // lay 100 dong
            out.add(list.get(i));

        return out;
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


    public static String converst(String s){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) == ',') res.append('.'); else res.append(s.charAt(i));
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

    public static void main(String[] args) throws IOException {
        String excelFilePath = "E:\\CODE\\Intellij\\Fuzzy\\src\\main\\java\\fuzzy\\input.xlsx";
        InputController inputController = new InputController();
        System.out.println(inputController.read(excelFilePath).size());
        List<DuLieu>list = inputController.read(excelFilePath);
        for(int i = 0; i < list.size(); i++)
            System.out.println(i + " " + list.get(i).toString());
    }
}
