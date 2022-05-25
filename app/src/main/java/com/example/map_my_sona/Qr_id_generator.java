package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.map_my_sona.manualComplaints.ManualComplaint_page;
import com.example.map_my_sona.rating.Rating_and_Feedback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.zxing.WriterException;

import java.io.OutputStream;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class Qr_id_generator extends AppCompatActivity {

    EditText etinput;
    MaterialButton btgenerate;
    ImageView ivoutput;
    MaterialButton qrsave;


    //
    OutputStream outputStream;


    //
    String TAG = "GenerateQRCode";

    String inputValue;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_id_generator);


        etinput=findViewById(R.id.et_input);
        btgenerate=findViewById(R.id.bt_generate);
        ivoutput=findViewById(R.id.iv_output);
        qrsave=findViewById(R.id.qrsave);

        ActivityCompat.requestPermissions(Qr_id_generator.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(Qr_id_generator.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        qrsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////                saveToGallery();
//                boolean qsave;
//                String result;
//                try {
//                    qsave = QRGSaver.save(savePath, etinput.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
////                    QRGSaver.save(savePath, etinput.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
//                    result = qsave ? "Image Saved to Gallery" : "Image Not Saved To Gallery";
//                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                    MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "code_scanner"
                            , null);
                    Toast.makeText(Qr_id_generator.this, "Qr Code Saved To Gallery", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Qr_id_generator.this, dashboard.class);
                startActivity(intent);
        }
        });

        btgenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String sText = etinput.getText().toString().trim();
//
//                MultiFormatWriter writer =new MultiFormatWriter();
//
//                try {
//                    BitMatrix matrix =writer.encode(sText, BarcodeFormat.QR_CODE,340,350);
//                    BarcodeEncoder encoder =new BarcodeEncoder();
//                    Bitmap bitmap =encoder.createBitmap(matrix);
//                    ivoutput.setImageBitmap(bitmap);
//                    InputMethodManager manager =(InputMethodManager)  getSystemService(
//                            Context.INPUT_METHOD_SERVICE
//                    );
//                    manager.hideSoftInputFromWindow(etinput.getApplicationWindowToken(),0);
//                } catch (WriterException e) {
//                    e.printStackTrace();
//                }

                inputValue = etinput.getText().toString().trim();
                if (inputValue.length() > 0) {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerDimension = width < height ? width : height;
                    smallerDimension = smallerDimension * 3 / 4;

                    qrgEncoder = new QRGEncoder(
                            inputValue, null,
                            QRGContents.Type.TEXT,
                            smallerDimension);
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        ivoutput.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        Log.v(TAG, e.toString());
                    }
                } else {
                    etinput.setError("Required");
                }
            }
//        }
        });


    };



//    private void saveToGallery(){
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) ivoutput.getDrawable();
//        Bitmap bitmap = bitmapDrawable.getBitmap();
//
////        FileOutputStream outputStream = null;
//        File file = Environment.getExternalStorageDirectory();
//        File dir = new File(file.getAbsolutePath() + "/MyPics");
//        dir.mkdirs();
//
//        String filename = String.format("%d.png",System.currentTimeMillis());
//        File outFile = new File(dir,filename);
//        try{
//            outputStream = new FileOutputStream(outFile);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
//        try{
//            outputStream.flush();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        try{
//            outputStream.close();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }

}



