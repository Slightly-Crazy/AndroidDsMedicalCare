package util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.*;

/**
 * Created by adrianlim on 15-06-17.
 */
public class UtilStorage {
    public static String saveToInternalSorage(Context context ,String childid, Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,childid+".jpg");

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(mypath.getAbsolutePath());
        return directory.getAbsolutePath();
    }

    public static Bitmap loadImageFromStorage(Context context, String childid)
    {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,childid+".jpg");
        Bitmap bp;

        if (mypath.exists()) {
            try {
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(mypath));
                bp = b;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Resources resources = context.getResources();
                final int resourceId = resources.getIdentifier("ic_launcher.png","mipmap",context.getPackageName());
                bp = BitmapFactory.decodeResource(resources,resourceId);
            }
        }
        else {
            Resources resources = context.getResources();
            final int resourceId = resources.getIdentifier("ic_launcher.png","mipmap",context.getPackageName());
            bp = BitmapFactory.decodeResource(resources,resourceId);
        }
        System.out.println(mypath.getAbsolutePath());
        return bp;
    }
}
