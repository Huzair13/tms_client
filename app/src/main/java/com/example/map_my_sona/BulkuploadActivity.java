package com.example.map_my_sona;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class BulkuploadActivity extends AppCompatActivity {
    // initialising the cell count as 2
    public static final int cellCount = 13;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    StorageReference ref;
    Button excel;
    int StickerSize;
    Context context;
    private StorageReference mStorageRef;


    ////////
    Button download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulkupload);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        excel = findViewById(R.id.excel);
        download=findViewById(R.id.excel_download);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://firebasestorage.googleapis.com/v0/b/map-my-sona.appspot.com/o/13_Clustering.pdf?alt=media&token=9ccaeab0-3610-4ddf-bae0-b9d06de71e38";
                downloadXL(BulkuploadActivity.this,"Tms_format",".pdf",Environment.getExternalStorageState(),url);
                //downloadfile();
            }
        });

        // click on excel to select a file
        excel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(BulkuploadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            StickerSize=snapshot.child("SonaStars").child("SonaCollege").child("stickers").getValue(Integer.class);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    selectfile();
                } else {
                    ActivityCompat.requestPermissions(BulkuploadActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
                }
            }
        });
    }



    private void downloadfile() {

//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReferenceFromUrl("gs://map-my-sona.appspot.com/Downloads");
//        StorageReference  islandRef = storageRef.child("Datastms.xlsx");
//
//        File rootPath = new File(Environment.getExternalStorageDirectory(), "Downloads");
//        if(!rootPath.exists()) {
//            rootPath.mkdirs();
//        }
//
//        final File localFile = new File(rootPath,"Datastms.xlsx");
//
//        islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                Log.e("firebase ",";local tem file created  created " +localFile.toString());
//                //  updateDb(timestamp,localFile.toString(),position);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Log.e("firebase ",";local tem file not created  created " +exception.toString());
//            }
//        });

//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReferenceFromUrl("gs://map-my-sona.appspot.com").child("Datastms.xlsx");
//
//
//        try {
//            final File localFile = File.createTempFile("images", "xlsx");
//            storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
//                    //mImageView.setImageBitmap(bitmap);
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                }
//            });
//        } catch (IOException e ) {}
//
//
//        final long ONE_MEGABYTE = 1024 * 1024;
//        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                //mImageView.setImageBitmap(bitmap);
//            }
//        });


//
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReferenceFromUrl("gs://map-my-sona.appspot.com");
//
//        ProgressDialog  pd = new ProgressDialog(this);
//        pd.setTitle("Nature.jpg");
//        pd.setMessage("Downloading Please Wait!");
//        pd.setIndeterminate(true);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        pd.show();
//
//
//        final File rootPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Downloads");
//
//        if (!rootPath.exists()) {
//            rootPath.mkdirs();
//        }
//
//
//        final File localFile = new File("gs://map-my-sona.appspot.com", "Datastms.xlsx");
//
//        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener <FileDownloadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                Log.e("firebase ", ";local tem file created  created " + localFile.toString());
//
//                if (!excel.isShown()){
//                    return;
//                }
//
//                if (localFile.canRead()){
//
//                    pd.dismiss();
//                }
//
//                Toast.makeText(BulkuploadActivity.this, "Download Completed", Toast.LENGTH_SHORT).show();
//                Toast.makeText(BulkuploadActivity.this, "Internal storage/MADBO/Nature.jpg", Toast.LENGTH_LONG).show();
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Log.e("firebase ", ";local tem file not created  created " + exception.toString());
//                Toast.makeText(BulkuploadActivity.this, "Download Incompleted", Toast.LENGTH_LONG).show();
//            }
//        });



//        DownloadManager downloadmanager = (DownloadManager) context.
//                getSystemService(Context.DOWNLOAD_SERVICE);
//        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/map-my-sona.appspot.com/o/Datastms.xlsx?alt=media&token=8f085f8a-a6d6-47e9-9339-bf51b1fce9fa");
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalFilesDir(context, "/Downloads", "TMS_format" + ".xlsx");
//        return downloadmanager.enqueue(request);


//
//        Log.d(TAG, "downloadFromPath:" + downloadPath); //Download firebase path

        //Initialize progress notification
        //showProgressNotification(getString(R.string.progress_downloading), 0, 0);

//        storageReference=FirebaseStorage.getInstance().getReference();
//        ref=storageReference.child("Datastms.xlsx");
//
//        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                String url=uri.toString();
//                downloadXL(BulkuploadActivity.this,"Tms_format","xlsx",DIRECTORY_DOWNLOADS,url);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });

    }

    private void downloadXL(Context context, String fname, String fileExtension, String destinationDirectory, String url) {

//        DownloadManager downloadmanager = (DownloadManager) context.
//                getSystemService(Context.DOWNLOAD_SERVICE);
//        Uri uri = Uri.parse(url);
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalFilesDir(context, destinationDirectory, fname + fileExtension);
//        downloadmanager.enqueue(request);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/map-my-sona.appspot.com/o/Datastms.xlsx?alt=media&token=8f085f8a-a6d6-47e9-9339-bf51b1fce9fa");
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);

    }


    // request for storage permission if not given
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectfile();
            } else {
                Toast.makeText(BulkuploadActivity.this, "Permission Not granted", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void selectfile() {
        // select the file from the file storage
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select File"), 102);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102) {
            if (resultCode == RESULT_OK) {
                String filepath = data.getData().getPath();
                // If excel file then only select the file
                if (filepath.endsWith(".xlsx") || filepath.endsWith(".xls")) {
                    readfile(data.getData());
                }
                // else show the error
                else {
                    Toast.makeText(this, "Please Select an Excel file to upload", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    ProgressDialog dialog;

    private void readfile(final Uri file) {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Uploading");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                final HashMap<String, Object> parentmap = new HashMap<>();

                try {
                    XSSFWorkbook workbook;

                    // check for the input from the excel file
                    try (InputStream inputStream = getContentResolver().openInputStream(file)) {
                        workbook = new XSSFWorkbook(inputStream);
                    }
                    final String timestamp = "" + System.currentTimeMillis();
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                    int rowscount = sheet.getPhysicalNumberOfRows();
                    if(rowscount-1<=StickerSize){
                        if (rowscount > 1) {
                            // check row wise data
                            for (int r = 1; r < rowscount; r++) {
                                Row row = sheet.getRow(r);
                                if (row.getPhysicalNumberOfCells() == cellCount) {

                                    // get cell data
                                    String UniqueID = getCellData(row, 0, formulaEvaluator);
                                    String dep_of_pro = getCellData(row, 1, formulaEvaluator);
                                    String ins_by=getCellData(row,2,formulaEvaluator);
                                    String ins_date=getCellData(row,3,formulaEvaluator);
                                    String location=getCellData(row,4,formulaEvaluator);
                                    String make=getCellData(row,5,formulaEvaluator);
                                    String model=getCellData(row,6,formulaEvaluator);
                                    String power_rating=getCellData(row,7,formulaEvaluator);
                                    String procurement=getCellData(row,8,formulaEvaluator);
                                    String sn_no =getCellData(row,9,formulaEvaluator);
                                    String wexpiry=getCellData(row,10,formulaEvaluator);
                                    String wperiod=getCellData(row,11,formulaEvaluator);
                                    String config=getCellData(row,12,formulaEvaluator);


                                    // initialise the hash map and put value of a and b into it
                                    HashMap<String, Object> quetionmap = new HashMap<>();

                                    quetionmap.put("uniqueID",UniqueID);
                                    quetionmap.put("dep_of_pro", dep_of_pro);
                                    quetionmap.put("ins_by",ins_by);
                                    quetionmap.put("ins_date",ins_date);
                                    quetionmap.put("location",location);
                                    quetionmap.put("make",make);
                                    quetionmap.put("model",model);
                                    quetionmap.put("power_rating",power_rating);
                                    quetionmap.put("procurement",procurement);
                                    quetionmap.put("sn_no",Double.parseDouble(sn_no));
                                    quetionmap.put("wexpiry",wexpiry);
                                    quetionmap.put("wperiod",wperiod);
                                    quetionmap.put("config",config);

                                    parentmap.put(UniqueID, quetionmap);
                                }
//                            else {
//                                dialog.dismiss();
//                                Toast.makeText(BulkuploadActivity.this, "row no. " + (r + 1) + " has incorrect data", Toast.LENGTH_LONG).show();
//                                return;
//                            }
                            }
                            // add the data in firebase if everything is correct
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // add the data according to timestamp
                                    FirebaseDatabase.getInstance().getReference().child("Datas").setValue(parentmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                dialog.dismiss();
                                                Toast.makeText(BulkuploadActivity.this, "Uploaded Successfully", Toast.LENGTH_LONG).show();
                                            } else {
                                                dialog.dismiss();
                                                Toast.makeText(BulkuploadActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                        // show the error if file is empty
                        else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                    Toast.makeText(BulkuploadActivity.this, "File is empty", Toast.LENGTH_LONG).show();
                                }
                            });
                            return;
                        }
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                Toast.makeText(BulkuploadActivity.this, "You are Limited to upload only " + StickerSize +" Data But you are trying to upload " + (rowscount-1), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                // show the error message if failed
                // due to file not found
                catch (final FileNotFoundException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BulkuploadActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                // show the error message if there
                // is error in input output
                catch (final IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BulkuploadActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
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
}