package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.VideoView;

public class Contact_act extends AppCompatActivity {
    private VideoView vv_video;
    private RatingBar rb_rate;
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
        rb_rate = findViewById(R.id.ratingBar);
        // edited here
        rb_rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                Toast.makeText(getBaseContext(), "Gracias por calificarnos!", Toast.LENGTH_SHORT).show();
                ratingBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void Dial(View view) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:957030166"));
        startActivity(i);
    }

    public void Map(View view) {
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }
}