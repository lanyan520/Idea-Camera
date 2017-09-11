package idea.analyzesystem.camera;

import android.hardware.Camera;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by idea on 2017/9/11.
 */
public abstract class PictureCallback implements Camera.PictureCallback {


    private static final String TAG = "OnPictureListener";

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), SystemClock.uptimeMillis()+".jpg");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            camera.startPreview();
            onTakePictureSucess(file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }

    public abstract void onTakePictureSucess(String picturePath);
}