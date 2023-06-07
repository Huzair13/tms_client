package com.example.tms_sona2;
import android.os.Environment;

import java.io.File;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelExporter {



    //final Context contextForResources = getContextForResources(context);

    public static void export() {
//        File sd = Environment.getExternalStorageDirectory();

        File sd = Environment.getDownloadCacheDirectory();
        String csvFile = "yourFile.xls";

        File directory = new File(sd.getAbsolutePath());


//        ContextWrapper cw = new ContextWrapper(ExcelExport);
//        String csvFile =cw.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
//        File directory = cw.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);

        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        try {

            //file path
//            File file = new File(directory, csvFile);
            java.io.File file = new java.io.File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    + "/Filename.xls");
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale(Locale.GERMAN.getLanguage(), Locale.GERMAN.getCountry()));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(file, wbSettings);

            //Excel sheetA first sheetA
            WritableSheet sheetA = workbook.createSheet("sheet A", 0);

            // column and row titles
            sheetA.addCell(new Label(0, 0, "sheet A 1"));
            sheetA.addCell(new Label(1, 0, "sheet A 2"));
            sheetA.addCell(new Label(0, 1, "sheet A 3"));
            sheetA.addCell(new Label(1, 1, "sheet A 4"));

            //Excel sheetB represents second sheet
            WritableSheet sheetB = workbook.createSheet("sheet B", 1);

            // column and row titles
            sheetB.addCell(new Label(0, 0, "sheet B 1"));
            sheetB.addCell(new Label(1, 0, "sheet B 2"));
            sheetB.addCell(new Label(0, 1, "sheet B 3"));
            sheetB.addCell(new Label(1, 1, "sheet B 4"));

            // close workbook
            workbook.write();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

