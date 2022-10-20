package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class BulkuploadActivity extends AppCompatActivity {

    public static final int cellCount=2;
    Button excel;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulkupload);

        excel=findViewById(R.id.excel);

//        excel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//                readExcelFromStorage(getApplicationContext(),"Book1");
//            }
//        });


//        excel = findViewById(R.id.excel);
//
//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        intent.setType("*/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        startActivityForResult(Intent.createChooser(intent, "Select File"), 102);
//
//        dbref= FirebaseDatabase.getInstance().getReference("testing");
//        XSSFWorkbook workbook = null;
//            XSSFSheet sheet= (XSSFSheet) workbook.getSheetAt(0);
//            FormulaEvaluator formulaEvaluator=workbook.getCreationHelper().createFormulaEvaluator();
//            int rowscount=sheet.getPhysicalNumberOfRows();
//            if(rowscount > 0){
//            // check row wise data
//            for (int r=0;r<rowscount;r++){
//                Row row=sheet.getRow(r);
//                if(row.getPhysicalNumberOfCells()==cellCount) {
//                    // get cell data
//                    String uniqueID = getCellData(row,0,formulaEvaluator);
//                    String model = getCellData(row,1,formulaEvaluator);
//                    String make = getCellData(row,2,formulaEvaluator);
//                    String warranty = getCellData(row,3,formulaEvaluator);
//
//                    testingBulk testing=new testingBulk(uniqueID,model,make,warranty);
//                    dbref.child(uniqueID).setValue(testing);
//
//
//                }
//                else {
//                    Toast.makeText(BulkuploadActivity.this,"row no. "+(r+1)+" has incorrect data",Toast.LENGTH_LONG).show();
//                    return;
//                }
//            }
//    }
}

    private String getCellData(Row row, int cellposition, FormulaEvaluator formulaEvaluator) {

        String value = "";

        // get cell from excel sheet
        Cell cell = row.getCell(cellposition);
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return value + cell.getBooleanCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return value + cell.getNumericCellValue();
            case Cell.CELL_TYPE_STRING:
                return value + cell.getStringCellValue();
            default:
                return value;
        }
    }

    public static void readExcelFromStorage(View.OnClickListener context, String fileName) {
    }
}