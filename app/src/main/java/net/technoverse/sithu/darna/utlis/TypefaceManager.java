package net.technoverse.sithu.darna.utlis;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.LruCache;

/**
 * Created by sithu on 10/29/16.
 */

public class TypefaceManager {

    private final LruCache<String, Typeface> mCache;
    private final AssetManager mAssetManager;

    private static final String SHIT_UNICODE = "pyidaungsu.ttf";
    private static final String SHIT_ZAWGYI = "szgpro.ttf";
    private static final String FONT_AWESOME = "fontawesome-webfont.ttf";

    public TypefaceManager( AssetManager assetManager) {
        mCache = new LruCache<>(3);
        mAssetManager = assetManager;
    }


    public Typeface getShitUnicde() {
        return getTypeface(SHIT_UNICODE);
    }

    public Typeface getShitZawgyi() {
        return getTypeface(SHIT_ZAWGYI);
    }

    public Typeface getFontAwesome() {
        return getTypeface(FONT_AWESOME);
    }


    private Typeface getTypeface(final String filename) {

        Typeface typeface = mCache.get(filename);
        if(typeface == null){
            typeface = typeface.createFromAsset(mAssetManager, "fonts/" + filename);
            mCache.put(filename, typeface);
        }
        return typeface;
    }


}
