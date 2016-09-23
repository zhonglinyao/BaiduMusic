package com.example.lanou3g.baidumusic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by dllo on 16/8/26.
 */
public class MyCricleImage extends ImageView {
    private boolean isCircle = false;
    private Paint paint;

    public MyCricleImage(Context context) {
        super(context);
    }

    public MyCricleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyCricleImage);
        isCircle = array.getBoolean(R.styleable.MyCricleImage_is_circle, false);
    }

    public MyCricleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isCircle) {
            BitmapDrawable drawable = (BitmapDrawable) getDrawable();
            if (drawable != null) {
                Bitmap bitmap = drawable.getBitmap();
                Bitmap cirlceBitmap = getCircleBitmap(bitmap);
                paint = new Paint();
                paint.setAntiAlias(true);
                Rect rect = new Rect(0, 0,
                        cirlceBitmap.getWidth(), cirlceBitmap.getHeight());
                canvas.drawBitmap(cirlceBitmap, rect, rect, paint);
            }
        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        Rect rect = new Rect(0, 0, bitmap.getWidth() < bitmap.getHeight() ? bitmap.getWidth() : bitmap.getHeight(), bitmap.getHeight());

        canvas.drawBitmap(bitmap, rect, rect, paint);

        return outBitmap;
    }
}
