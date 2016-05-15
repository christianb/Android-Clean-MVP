package de.bunk.util;

import android.content.Context;

public final class DrawUtil {

    private DrawUtil() {}

    public static float dpToPx(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
