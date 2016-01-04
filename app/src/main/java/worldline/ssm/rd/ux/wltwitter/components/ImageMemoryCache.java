package worldline.ssm.rd.ux.wltwitter.components;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by cassar on 04/01/16.
 */
public class ImageMemoryCache {

    private LruCache<String,Bitmap> mMemoryCache;

    public ImageMemoryCache(int maxCacheSize){
        mMemoryCache = new LruCache<String,Bitmap>(maxCacheSize){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return (value.getRowBytes() * value.getHeight()) / 1024;
            }
        };

    }

    public void addBitMapToMemoryCache(String key, Bitmap value){
        if(getBitmapFromMemoryCache(key) == null){
            mMemoryCache.put(key,value);
        }
    }

    public Bitmap getBitmapFromMemoryCache(String key){
        return mMemoryCache.get(key);
    }
}
