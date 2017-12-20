package me.leon.uploadlibs;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//import com.luck.picture.lib.PictureSelector;
//import com.luck.picture.lib.config.PictureConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);


    }

    public void open(View view) {
//        PictureSelector.create(this)
//                .openGallery(PictureConfig.TYPE_IMAGE)
//                .enableCrop(true)
//                .maxSelectNum(3)
//                .minSelectNum(0)
//                .forResult(888);
    }
}
