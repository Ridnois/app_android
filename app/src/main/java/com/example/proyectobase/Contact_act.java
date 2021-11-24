package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Contact_act extends AppCompatActivity {
    private VideoView vv_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        vv_video = findViewById(R.id.vv_video);
        String videoRoute = "android.resource://"+ getPackageName() + "/" +R.raw.video;
        Uri uri = Uri.parse(videoRoute);
        vv_video.setVideoURI(uri);

        MediaController media = new MediaController(this);
        vv_video.setMediaController(media);

    }


}