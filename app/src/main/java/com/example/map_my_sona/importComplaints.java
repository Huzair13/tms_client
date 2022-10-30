package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.map_my_sona.complaints.Complaint_details;
import com.google.android.gms.common.internal.Constants;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class importComplaints extends AppCompatActivity {

    // Global Variables
    private static Cell cell;
    private static Sheet sheet;

    private static String EXCEL_SHEET_NAME = "Sheet1";
    private static HSSFWorkbook workbook;
    private static HSSFCellStyle headerCellStyle;

    List<Complaint_details> data1;

    Button comdet_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_complaints);

        comdet_Button=findViewById(R.id.complaintsdata);

        comdet_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportDataIntoWorkbook(getApplicationContext(),"Complaint details",data1);
            }
        });

    }

    public static boolean exportDataIntoWorkbook(Context context, String fileName,
                                                 List<Complaint_details> dataList) {
        boolean isWorkbookWrittenIntoStorage;

        // Check if available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.e("Message", "Storage not available or read only");
            return false;
        }

        // Creating a New HSSF Workbook (.xls format)
        workbook = new HSSFWorkbook();

        //setHeaderCellStyle();

        // Creating a New Sheet and Setting width for each column
        sheet = workbook.createSheet("Complaints Details");
        sheet.setColumnWidth(0, (15 * 400));
        sheet.setColumnWidth(1, (15 * 400));
        sheet.setColumnWidth(2, (15 * 400));
        sheet.setColumnWidth(3, (15 * 400));
        sheet.setColumnWidth(4, (15 * 400));

        setHeaderRow();
        fillDataIntoExcel(dataList);
        isWorkbookWrittenIntoStorage = storeExcelInStorage(context, fileName);

        return isWorkbookWrittenIntoStorage;
    }

    /**
     * Checks if Storage is READ-ONLY
     *
     * @return boolean
     */
    private static boolean isExternalStorageReadOnly() {
        String externalStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(externalStorageState);
    }

    /**
     * Checks if Storage is Available
     *
     * @return boolean
     */
    private static boolean isExternalStorageAvailable() {
        String externalStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(externalStorageState);
    }

    /**
     * Setup header cell style
     */
//    private static void setHeaderCellStyle() {
//        headerCellStyle = workbook.createCellStyle();
//        headerCellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
//    }

    /**
     * Setup Header Row
     */
    private static void setHeaderRow() {
        Row row = sheet.createRow(0);

        cell = row.createCell(0);
        cell.setCellValue("Department");
        //cell.setCellStyle(headerCellStyle);

        cell = row.createCell(1);
        cell.setCellValue("Unique ID");
        //cell.setCellStyle(headerCellStyle);

        cell = row.createCell(2);
        cell.setCellValue("Complaint");
        //cell.setCellStyle(headerCellStyle);

        cell = row.createCell(3);
        cell.setCellValue("Date");

        cell = row.createCell(4);
        cell.setCellValue("Status");
    }

    /**
     * Fills Data into Excel Sheet
     * <p>
     * NOTE: Set row index as i+1 since 0th index belongs to header row
     *
     * @param dataList - List containing data to be filled into excel
     */
    private static void fillDataIntoExcel(List<Complaint_details> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            // Create a New Row for every new entry in list
            Row rowData = sheet.createRow(i + 1);

            // Create Cells for each row
            cell = rowData.createCell(0);
            cell.setCellValue(dataList.get(i).getDep_of_pro());

            cell = rowData.createCell(1);
            cell.setCellValue(dataList.get(i).getUniqueId());

            cell = rowData.createCell(2);
            cell.setCellValue(dataList.get(i).getCom_txt());

            cell = rowData.createCell(3);
            cell.setCellValue(dataList.get(i).getDate());

            cell = rowData.createCell(4);
            cell.setCellValue(dataList.get(i).getStatus());
        }
    }

    /**
     * Store Excel Workbook in external storage
     *
     * @param context  - application context
     * @param fileName - name of workbook which will be stored in device
     * @return boolean - returns state whether workbook is written into storage or not
     */
    private static boolean storeExcelInStorage(Context context, String fileName) {
        boolean isSuccess;
        File file = new File(context.getExternalFilesDir(null), fileName);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            Log.e("Message", "Writing file" + file);
            isSuccess = true;
        } catch (IOException e) {
            Log.e("Message", "Error writing Exception: ", e);
            isSuccess = false;
        } catch (Exception e) {
            Log.e("Message", "Failed to save file due to Exception: ", e);
            isSuccess = false;
        } finally {
            try {
                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return isSuccess;
    }


}