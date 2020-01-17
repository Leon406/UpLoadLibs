package me.leon.uploadlibs;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.luck.picture.lib.tools.SdkVersionUtils;

import java.util.List;

import io.reactivex.Single;
import me.leon.rxbus.RxBus2;

import static com.luck.picture.lib.tools.PictureFileUtils.getDataColumn;
import static com.luck.picture.lib.tools.PictureFileUtils.isDownloadsDocument;
import static com.luck.picture.lib.tools.PictureFileUtils.isExternalStorageDocument;
import static com.luck.picture.lib.tools.PictureFileUtils.isGooglePhotosUri;
import static com.luck.picture.lib.tools.PictureFileUtils.isMediaDocument;

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
        Single<List<String>> listSingle = ImagePicker.openDynamicGallery(this);
        if (listSingle !=null) {
            listSingle.subscribe(l -> {
                Log.w("result", l.toString());
            },throwable -> Log.e("eeeeee",Log.getStackTraceString(throwable)));
        }

//        RxBus2.getDefault().postSticky("Hello");
//
//        Main2Activity.start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus2.getDefault().unregister();
    }

    public void tt(View view) {
        Log.d("getPath","start");
        getPath(this,Uri.parse("/storage/emulated/0/DCIM/Camera/IMG_20200108_104354.jpg"));

    }
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        Log.d("getPath","in");
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                Log.d("getPath","isExternalStorageDocument");
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    if (SdkVersionUtils.checkedAndroid_Q()) {
                        Log.d("getPath","q priamry");
                        return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/" + split[1];
                    } else {
                        Log.d("getPath","below q priamry");
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                Log.d("getPath","dowload");
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

}
