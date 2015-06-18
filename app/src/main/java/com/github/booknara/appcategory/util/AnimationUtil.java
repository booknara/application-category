package com.github.booknara.appcategory.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;

/**
 *
 * @author : Daehee Han (@daniel_booknara)
 * @version : 1.0.0
 */
public class AnimationUtil {
    private AnimationUtil() { }

	public static void fadeInView(Context context, ImageView view, Drawable start, Drawable end, int duration) {
        fadeInView(context, view, BitmapUtil.convertDrawableToBitmap(start), BitmapUtil.convertDrawableToBitmap(end), duration);
	}

    public static void fadeInView(Context context, ImageView view, Bitmap start, Drawable end, int duration) {
        fadeInView(context, view, start, BitmapUtil.convertDrawableToBitmap(end), duration);
    }
    public static void fadeInView(Context context, ImageView view, Drawable start, Bitmap end, int duration) {
        fadeInView(context, view, BitmapUtil.convertDrawableToBitmap(start), end, duration);
    }

    private static void fadeInView(Context context, ImageView view, Bitmap start, Bitmap end, int duration) {
        Drawable[] layers = new Drawable[2];
        layers[0] = new BitmapDrawable(context.getResources(), start);
        layers[1] = new BitmapDrawable(context.getResources(), end);

        TransitionDrawable transitionDrawable = new TransitionDrawable(layers);
        view.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(duration);
    }
    public static void fadeInBackgroundView(Context context, ImageView view, Drawable start, Drawable end, int duration) {
        Drawable[] layers = new Drawable[2];
        layers[0] = new BitmapDrawable(context.getResources(), BitmapUtil.convertDrawableToBitmap(start));
        layers[1] = new BitmapDrawable(context.getResources(), BitmapUtil.convertDrawableToBitmap(end));

        TransitionDrawable transitionDrawable = new TransitionDrawable(layers);
        view.setBackgroundDrawable(transitionDrawable);
        transitionDrawable.startTransition(duration);
    }

    public static void fadeInBackgroundView(Context context, ImageView view, int transitionResid, int duration) {
        view.setBackgroundResource(transitionResid);
        TransitionDrawable background = (TransitionDrawable) view.getBackground();
        background.startTransition(duration);
    }
}
