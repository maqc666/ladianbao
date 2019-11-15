package com.yifu.ladianbao.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;

import com.yifu.ladianbao.R;
import com.yifu.ladianbao.net.UrlUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author 与天同行的观测者
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2017/12/11 0011 14:18
 */

public class ImageLoader {

    public static boolean isGifPlaying = false;

    public static void loadHead(final Context context, ImageView view, String url) {
        try {
            if (!TextUtils.isEmpty(url) && !url.contains("http"))
                url = UrlUtils.domain + url;
            RequestOptions options = new RequestOptions();
            options.centerCrop().placeholder(R.mipmap.img_touxzw).error(R.mipmap.img_touxzw);
            Glide.with(context).asBitmap()
                    .load(url)
                    .apply(options)
                    .into(new BitmapImageViewTarget(view) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            view.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void loadLocalHead(final Context context, ImageView view, int res) {
        try {
            RequestOptions options = new RequestOptions();
            Glide.with(context).asBitmap()
                    .load(res)
                    .apply(options)
                    .into(new BitmapImageViewTarget(view) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            view.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImage(Context context, ImageView view, String url) {
        try {
            if (!TextUtils.isEmpty(url) && !url.contains("http")) {
                url = UrlUtils.domain + url;
            }
//            Log.e("tenda", "url:" + url);
            RequestOptions options = new RequestOptions();
            options.centerCrop().placeholder(R.mipmap.img_cikazw).error(R.mipmap.img_cikazw);
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadHeadLocal(final Context context, ImageView view, String url) {
        try {
            RequestOptions options = new RequestOptions();
            options.centerCrop().placeholder(R.mipmap.img_cikazw).error(R.mipmap.img_cikazw).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
            Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .apply(options)
                    .into(new BitmapImageViewTarget(view) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            view.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImageLocal(final Context context, ImageView view, String url) {
        try {
            RequestOptions options = new RequestOptions();
            options.centerCrop().placeholder(R.mipmap.img_cikazw).error(R.mipmap.img_cikazw).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
            Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .apply(options)
                    .into(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadHeadDefault(final Context context, ImageView view, String url, int defaultRes) {
        try {
            if (!TextUtils.isEmpty(url) && !url.contains("http"))
                url = UrlUtils.domain + url;
            RequestOptions options = new RequestOptions();
            options.centerCrop().placeholder(defaultRes).error(defaultRes);
            Glide.with(context).asBitmap()
                    .load(url)
                    .apply(options)
                    .into(new BitmapImageViewTarget(view) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            view.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImageDefault(Context context, ImageView view, String url, int defaultRes) {
        try {
            if (!TextUtils.isEmpty(url) && !url.contains("http"))
                url = UrlUtils.domain + url;
            RequestOptions options = new RequestOptions();
            options.centerCrop().placeholder(defaultRes).error(defaultRes);
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载gif图片，带监听
     *
     * @param context
     * @param view
     * @param res
     */
    public static void loadGifImage(Context context, ImageView view, Integer res, LinearLayout llVip) {
        try {
            RequestOptions options = new RequestOptions();
            options.centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context)
                    .asGif()
                    .load(res)
                    .apply(options)
                    .listener(new RequestListener<GifDrawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                            try {
                                isGifPlaying = true;
                                Field gifStateField = GifDrawable.class.getDeclaredField("state");
                                gifStateField.setAccessible(true);
                                Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
                                Field gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader");
                                gifFrameLoaderField.setAccessible(true);
                                Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
                                Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder");
                                gifDecoderField.setAccessible(true);
                                Class gifDecoderClass = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder");
                                Object gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)));
                                Method getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", int.class);
                                getDelayMethod.setAccessible(true);
                                ////设置播放次数
                                resource.setLoopCount(1);
                                //获得总帧数
                                int count = resource.getFrameCount();
                                int delay = 0;
                                for (int i = 0; i < count; i++) {
                                    //计算每一帧所需要的时间进行累加
                                    delay += (int) getDelayMethod.invoke(gifDecoder, i);
                                }
                                view.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        llVip.setVisibility(View.GONE);
                                        isGifPlaying = false;
                                    }
                                }, delay);
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                    })
                    .into(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
