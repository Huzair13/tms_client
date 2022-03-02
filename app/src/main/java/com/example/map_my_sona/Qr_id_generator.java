package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Qr_id_generator extends AppCompatActivity {

    EditText etinput;
    MaterialButton btgenerate;
    ImageView ivoutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_id_generator);

        etinput=findViewById(R.id.et_input);
        btgenerate=findViewById(R.id.bt_generate);
        ivoutput=findViewById(R.id.iv_output);

        btgenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = etinput.getText().toString().trim();

                MultiFormatWriter writer =new MultiFormatWriter();

                try {
                    BitMatrix matrix =writer.encode(sText, BarcodeFormat.QR_CODE,350,350);

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
}