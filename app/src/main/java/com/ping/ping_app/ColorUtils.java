package com.ping.ping_app;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import java.util.Random;

/**
 * Created by lupin on 9/8/2019.
 */

public class ColorUtils {
    public static int getViewHolderBackgroundColorFromInstance(Context context, int instanceNum) {
        Random rnd = new Random();
        int color = Color.argb(70, rnd.nextInt(70), rnd.nextInt(70), rnd.nextInt(70));
        return color;
        }
}
