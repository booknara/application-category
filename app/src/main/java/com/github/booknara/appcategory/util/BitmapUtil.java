package com.github.booknara.appcategory.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.github.booknara.appcategory.BuildConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtil {
	private static final String CNAME = BitmapUtil.class.getSimpleName();
	
	// Suppress default constructor for noninstantiability
    private BitmapUtil() { }    // This constructor will never be invoked
	

	public static Bitmap getResizedBitmap(Bitmap image, int bitmapWidth, int bitmapHeight) {
		return Bitmap.createScaledBitmap(image, bitmapWidth, bitmapHeight, true);
	}
	
	public static Bitmap getCircularBitmap(Bitmap bitmap) {
	    Bitmap b = null;
	    
	    try {
		    if (bitmap.getWidth() > bitmap.getHeight()) {
		        b = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Config.ARGB_8888);
		    } else {
		        b = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Config.ARGB_8888);
		    }
		    
		    Canvas cvs = new Canvas(b);
		    final int color = Color.WHITE;
		    final Paint paint = new Paint();
		    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		    
		    float radius = 0;

		    if (bitmap.getWidth() > bitmap.getHeight()) {
		    	radius = bitmap.getHeight() / 2;
		    } else {
		    	radius = bitmap.getWidth() / 2;
		    }

		    paint.setAntiAlias(true);
		    cvs.drawARGB(0, 0, 0, 0);
		    paint.setColor(color);
		    cvs.drawCircle(radius, radius, radius, paint);
//		    cvs.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
		    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		    cvs.drawBitmap(bitmap, rect, rect, paint);
		    
		    return b;	    	
	    } catch (NullPointerException e) {
			if (BuildConfig.DEBUG)
				Logger.e(CNAME, ExceptionUtil.exception(e));
	    } catch (OutOfMemoryError o) {
			if (BuildConfig.DEBUG)
				Logger.e(CNAME, "OutOfMemoryError : "+ o.toString());
			
			System.gc();
	    }
	    
	    return null;
	}
	
	/**
     * Convert bitmap to the grayscale
     * http://androidsnippets.com/convert-bitmap-to-grayscale
     *
     * @param bmpOriginal Original bitmap
     * @return Grayscale bitmap
     */
    public static Bitmap toGrayscale(Bitmap bmpOriginal) {
        final int height = bmpOriginal.getHeight();
        final int width = bmpOriginal.getWidth();

        final Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        final Canvas c = new Canvas(bmpGrayscale);
        final Paint paint = new Paint();
        final ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        final ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

    public static boolean saveDrawabletoFile(Context c, Drawable d, File file) {
    	//create a file to write bitmap data
    	try {
			file.createNewFile();
	    	//Convert bitmap to byte array
	    	Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
	    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    	bitmap.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
	    	byte[] bitmapdata = bos.toByteArray();

	    	//write the bytes in file
	    	FileOutputStream fos = new FileOutputStream(file);
	    	fos.write(bitmapdata);
	    	fos.flush();
	    	fos.close();
	    	return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return false;
    }

    public static Bitmap convertDrawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

}
