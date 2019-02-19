package me.leon.uploadlibs;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import me.leon.rxbus.RxBus2;

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

        RxBus2.getDefault().post("Hello");
//        PictureSelector.create(this)
//                .openGallery(PictureConfig.TYPE_IMAGE)
//                .enableCrop(true)
//                .maxSelectNum(3)
//                .minSelectNum(0)
//                .forResult(888);
    }

    public void post(View view) {
        ImagePicker.openDynamicGallery(this)
                .subscribe(l -> {
                    Log.w("result", l.toString());
                });
//        RxBus2.getDefault().postSticky("Hello");
//
//        Main2Activity.start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus2.getDefault().unregister();
    }
}
