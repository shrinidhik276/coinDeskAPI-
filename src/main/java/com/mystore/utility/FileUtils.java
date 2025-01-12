package com.mystore.utility;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileUtils {

    public static Object getJsonFile(String fileName) throws IOException, ParseException {
        File f = Res.getFile("/src/test/resources/data/" + fileName + ".json");
        Object fileobj = new JSONParser().parse(new FileReader(f));

        return fileobj;
    }
    public static HashMap<String,String>getJsonCredentials(String filename) throws IOException, ParseException {
        File f=Res.getFile("/src/test/resources/data/" +filename + ".json");
        Object fileobj= new JSONParser().parse(new FileReader(f));
        // Convert to HashMap
        HashMap<String, String> data = (HashMap<String, String>) fileobj;
        return data;
    }
    public static List<HashMap<String,String>>getexcelData(String filename) throws IOException, ParseException {
        File file = new File("C:/Users/107750/Documents/UI Automation/MyStoreApplication/src/test/resources/data/credentials.xlsx");
        FileInputStream fis = new FileInputStream(file);
        // Create a workbook and get the sheet
        Workbook workbook = null;

        if (filename.endsWith(".xls")) {
            workbook = new HSSFWorkbook(fis);  // .xls file, use HSSFWorkbook
        } else if (filename.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(fis);  // .xlsx file, use XSSFWorkbook
        } else {
            throw new IllegalArgumentException("Unsupported file format. Only .xls and .xlsx are supported.");
        }

        Sheet sheet =workbook.getSheetAt(0);
        // Create a HashMap to store the data
        List<HashMap<String,String>>data=new ArrayList<>();
        // Assuming the first row contains headers (e.g., username, password)
        Row headerrow=sheet.getRow(0);
        for(int i=1;i<sheet.getPhysicalNumberOfRows();i++){
            Row row= sheet.getRow(i);
            HashMap<String, String> rowData = new HashMap<>();
            // Loop through each cell in the row
            for(int j=0;j<row.getPhysicalNumberOfCells();j++) {
                String header = headerrow.getCell(j).getStringCellValue();
                String value = row.getCell(j).getStringCellValue();
                rowData.put(header, value);  // Store data in row map
            }
                // Put the data into the HashMap
               data.add(rowData);
            }

        // Close the workbook and file input stream
        workbook.close();
        fis.close();

        return data;


    }

    public static String getCardData(String cardType, String fieldValue) throws IOException {
        Gson gson = new Gson();
        JsonElement jo = gson.fromJson(new FileReader(Res.getFile("/src/test/resources/data/cards.json")), JsonElement.class);
        String value = String.valueOf(jo.getAsJsonObject().getAsJsonObject(cardType).get(fieldValue)).replaceAll("\"", "");
        return value;
    }

    public static String getDataValue(String filename, String fieldValue) throws IOException {
        Gson gson = new Gson();
        JsonElement jo = gson.fromJson(new FileReader(Res.getFile("/src/test/resources/data/" + filename + ".json")), JsonElement.class);
        String value = String.valueOf(jo.getAsJsonObject().get(fieldValue)).replaceAll("\"", "");
        return value;
    }
    public static String getDataValueWithCondition(String filename,String env, String fieldValue) throws IOException {
        Gson gson = new Gson();
        JsonElement jo = gson.fromJson(new FileReader(Res.getFile("/src/test/resources/data/" + filename + ".json")), JsonElement.class);
        String value = String.valueOf(jo.getAsJsonObject().getAsJsonObject(env).get(fieldValue)).replaceAll("\"", "");
        return value;
    }
    public static String getDataValueWithEnvCondition(String filename, String env, String condition, String fieldValue) throws IOException {
        Gson gson = new Gson();
        JsonElement jo = gson.fromJson(new FileReader(Res.getFile("/src/test/resources/data/" + filename + ".json")), JsonElement.class);
        String value = String.valueOf(jo.getAsJsonObject().getAsJsonObject(env).getAsJsonObject(condition).get(fieldValue)).replaceAll("\"", "");
        return value;
    }

}