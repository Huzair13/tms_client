package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ExportComplaints extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_complaints);
        btn = findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File excel = new File("/storage/emulated/0/yourFile.xlsx");
                    FileInputStream fis = new FileInputStream(excel);
                    XSSFWorkbook book = new XSSFWorkbook(fis);
                    XSSFSheet sheet = book.getSheetAt(0);
                    Iterator<Row> itr = sheet.iterator();
                    // Iterating over Excel file in Java
                    while (itr.hasNext()) {
                        Row row = itr.next();
                    // Iterating over each column of Excel file
                        Iterator<Cell> cellIterator = row.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    System.out.print(cell.getStringCellValue() + "\t");
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    System.out.print(cell.getNumericCellValue() + "\t");
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    System.out.print(cell.getBooleanCellValue() + "\t");
                                    break;
                                default:
                            }
                        }
                        System.out.println("");
                    }
                    // writing data into XLSX file
                    Map<String, Object[]> newData = new HashMap<String, Object[]>();
                    newData.put("7", new Object[] { 7d, "Sonya", "75K", "SALES",
                            "Rupert" });
                    newData.put("8", new Object[] { 8d, "Kris", "85K", "SALES",
                            "Rupert" });
                    newData.put("9", new Object[] { 9d, "Dave", "90K", "SALES",
                            "Rupert" });
                    Set<String> newRows = newData.keySet();
                    int rownum = sheet.getLastRowNum();
                    for (String key : newRows) {
                        Row row = sheet.createRow(rownum++);
                        Object[] objArr = newData.get(key);
                        int cellnum = 0;

                        for (Object obj : objArr) {
                            Cell cell = row.createCell(cellnum++);
                            if (obj instanceof String) {
                                cell.setCellValue((String) obj);
                            } else if (obj instanceof Boolean) {
                                cell.setCellValue((Boolean) obj);
                            } else if (obj instanceof Date) {
                                cell.setCellValue((Date) obj);
                            } else if (obj instanceof Double) {
                                cell.setCellValue((Double) obj);
                            }
                        }
                    }
                   // open an OutputStream to save written data into Excel file
                    FileOutputStream os = new FileOutputStream(excel);
                    book.write(os);
                    System.out.println("Writing on Excel file Finished ...");
                   // Close workbook, OutputStream and Excel file to prevent leak
                    os.close();
                    book.close();
                    fis.close();
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}