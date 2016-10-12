package com.example.lanou3g.baidumusic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 16/10/10.
 */
public class Tools {
    public static int calculateInSampleSize(BitmapFactory.Options options, int width, int height) {
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int widthInSampleSize = outWidth / width;
        int heightInSampleSize = outHeight / height;
        return Math.max(widthInSampleSize, heightInSampleSize);
    }

    public static String getFormatedDateTime(long dateTime) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("mm:ss");
        return sDateFormat.format(new Date(dateTime + 0));
    }

    public static Bitmap changeBackgroundImage(Bitmap sentBitmap, float radius) {
        if (Build.VERSION.SDK_INT > 16 && sentBitmap != null) {
            Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
            final RenderScript rs = RenderScript.create(MyApp.getmContext());
            final Allocation input = Allocation.createFromBitmap(rs, sentBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT);
            final Allocation output = Allocation.createTyped(rs, input.getType());
            final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setRadius(radius);
            script.setInput(input);
            script.forEach(output);
            output.copyTo(bitmap);
            return bitmap;
        }
        return null;
    }
}
