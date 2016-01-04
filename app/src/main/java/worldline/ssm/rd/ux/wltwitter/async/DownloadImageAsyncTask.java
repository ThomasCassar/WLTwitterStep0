package worldline.ssm.rd.ux.wltwitter.async;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import worldline.ssm.rd.ux.wltwitter.components.ImageMemoryCache;
import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;

/**
 * Created by cassar on 04/01/16.
 */
public class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private final ImageView imageView;

    private final ImageMemoryCache mImageMemoryCache;

    public DownloadImageAsyncTask(ImageView imageView, ImageMemoryCache mImageMemoryCache) {
        this.imageView = imageView;
        this.mImageMemoryCache = mImageMemoryCache;
    }


    @Override
    protected Bitmap doInBackground(String... params) {

        if (params !=null){
            final String imageUrl = params[0];

            try{
                final Bitmap bitmap = TwitterHelper.getTwitterUserImage(imageUrl);
                if (mImageMemoryCache != null){
                    mImageMemoryCache.getBitmapFromMemoryCache(imageUrl);
                }
                return bitmap;
            } catch ( Exception e){
                e.printStackTrace();
            }
         }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
        }
    }
}
