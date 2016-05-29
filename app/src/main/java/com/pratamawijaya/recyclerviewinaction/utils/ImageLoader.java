package com.pratamawijaya.recyclerviewinaction.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * Created by pratama on 5/29/16.
 */
public class ImageLoader {

  public static void loadImage(Context context, ImageView imageView, String url) {
    Glide.with(context).load(url).into(imageView);
  }
}
