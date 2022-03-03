package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class Qr_id_generator extends AppCompatActivity {

    EditText etinput;
    MaterialButton btgenerate;
    ImageView ivoutput;
    MaterialButton qrsave;

    //
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    QRGEncoder qrgEncoder;
    Bitmap bitmap;


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
                boolean save;
                String result;
                try {
                    save = QRGSaver.save(savePath, etinput.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
                    result = save ? "Image Saved" : "Image Not Saved";
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });





        btgenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = etinput.getText().toString().trim();

                MultiFormatWriter writer =new MultiFormatWriter();

                try {
                    BitMatrix matrix =writer.encode(sText, BarcodeFormat.QR_CODE,340,350);

                    BarcodeEncoder encoder =new BarcodeEncoder();

                    Bitmap bitmap =encoder.createBitmap(matrix);

                    ivoutput.setImageBitmap(bitmap);

                    InputMethodManager manager =(InputMethodManager)  getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );

                    manager.hideSoftInputFromWindow(etinput.getApplicationWindowToken(),0);

                } catch (WriterException e) {
                    e.printStackTrace();
                }


            }
        });



    }

    //

//    private void saveToGallery(){
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) ivoutput.getDrawable();
//        Bitmap bitmap = bitmapDrawable.getBitmap();
//
//        FileOutputStream outputStream = null;
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
