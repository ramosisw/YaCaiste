package mx.itson.yacaiste.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jcramos
 * ramos.isw@gmail.com
 * Created by jcramos on 10/11/2015.
 */
public class Utils {
    private static String mCurrentPhotoPath;

    public static File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory("mx.itson.yacaiste.android");
        if (storageDir.exists() || storageDir.mkdir()) {
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
            // Save a file: path for use with ACTION_VIEW intents
            setmCurrentPhotoPath(image.getAbsolutePath());
            return image;
        }
        return null;
    }

    public static String getmCurrentPhotoPath() {
        return mCurrentPhotoPath;
    }

    public static void setmCurrentPhotoPath(String mCurrentPhotoPath) {
        Utils.mCurrentPhotoPath = mCurrentPhotoPath;
    }
}
