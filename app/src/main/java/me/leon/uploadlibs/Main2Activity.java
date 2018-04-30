package me.leon.uploadlibs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import me.leon.rxbus.RxBus2;

public class Main2Activity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, Main2Activity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
Log.d("Main2Activity", "onCreate");
        RxBus2.getDefault().registerStickyEvent(String.class)
                .subscribe(aInt -> Toast.makeText(this, "Main2Activity"+aInt, Toast.LENGTH_SHORT).show());
    }

    public void postNormal(View view) {
        RxBus2.getDefault().post("Lala");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RxBus2.getDefault().unregister();
        Log.d("Main2Activity", "onDestroy");
    }

}
