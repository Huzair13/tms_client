package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class bulkUpload extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_upload);
        setupHyperlink();


        VideoView videoView = findViewById(R.id.video_view);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.seekTo( 1 );

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);


    }
    private void setupHyperlink() {
        TextView linkTextView = findViewById(R.id.activity_main_link);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView.setLinkTextColor(Color.BLUE);
    }
}