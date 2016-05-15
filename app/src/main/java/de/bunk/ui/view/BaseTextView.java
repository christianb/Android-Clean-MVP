package de.bunk.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.bunk.R;

public class BaseTextView extends TextView {

    private static final String BUNDLE_KEY_FONT_TYPE = "bundle_font_type";

    public static final int FONT_LIGHT = 0;
    public static final int FONT_REGULAR = 1;
    public static final int FONT_BOLD = 2;

    @IntDef({ FONT_LIGHT, FONT_REGULAR, FONT_BOLD })
    @Retention(RetentionPolicy.SOURCE)
    public @interface FontType { }

    private static Typeface typeFaceLight;
    private static Typeface typeFaceRegular;
    private static Typeface typeFaceBold;

    @FontType
    private int fontType;

    public BaseTextView(final Context context) {
        super(context);
    }

    public BaseTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        applyFont(context, attrs);
    }

    public BaseTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        applyFont(context, attrs);
    }

    private void applyFont(final Context context, final AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseTextView);
        final Integer fontType = typedArray.getInteger(R.styleable.BaseTextView_fontType, FONT_REGULAR);

        typedArray.recycle();

        Typeface font = null;

        // if font is set to MEDIUM, set default to ROBOTO-Light
        if (fontType == FONT_LIGHT) {
            font = getTypeFaceLight();
        }

        // if no font is set, set default to ROBOTO-Regular
        if (fontType == FONT_REGULAR) {
            font = getTypeFaceRegular();
        }

        // if font is set to MEDIUM, set default to ROBOTO-Regular
        if (fontType == FONT_BOLD) {
            font = getTypeFaceBold();
        }

        // noinspection ResourceType
        this.fontType = fontType;

        setTypeface(font, Typeface.NORMAL);
    }

    public void applyFont(@FontType final int fontType) {

        Typeface typeface = getTypeFaceRegular();
        switch (fontType) {

            case FONT_LIGHT :
                typeface = getTypeFaceLight();
                break;

            case FONT_BOLD :
                typeface = getTypeFaceBold();
                break;
        }

        this.fontType = fontType;

        setTypeface(typeface);
    }

    public Typeface getTypeFaceLight() {
        if (typeFaceLight == null) {
            typeFaceLight = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Light.ttf");
        }

        return typeFaceLight;
    }

    protected Typeface getTypeFaceRegular() {
        if (typeFaceRegular == null) {
            typeFaceRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        }

        return typeFaceRegular;
    }

    public Typeface getTypeFaceBold() {
        if (typeFaceBold == null) {
            typeFaceBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");
        }

        return typeFaceBold;
    }

    @Override
    public Parcelable onSaveInstanceState() {

        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt(BUNDLE_KEY_FONT_TYPE, this.fontType);
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {

        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;

            // noinspection ResourceType
            this.fontType = bundle.getInt(BUNDLE_KEY_FONT_TYPE);

            applyFont(fontType);

            state = bundle.getParcelable("instanceState");
        }

        super.onRestoreInstanceState(state);
    }
}
