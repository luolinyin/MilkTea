package com.xiaozhi.frame.tool.imageloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class LoaderImageNet {
    protected ImageLoader mImageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    public LoaderImageNet() {

        this(0, false);
    }

    public LoaderImageNet(int imageOnLoading) {

        this(imageOnLoading, imageOnLoading, false, false, new SimpleBitmapDisplayer());
    }


    public LoaderImageNet(int imageOnLoading, boolean isCacheOnDisc) {

        this(imageOnLoading, false, isCacheOnDisc);
    }


    public LoaderImageNet(int imageOnLoading, boolean isCacheInMemory, boolean isCacheOnDisc) {

        this(imageOnLoading, imageOnLoading, isCacheInMemory, isCacheOnDisc, new SimpleBitmapDisplayer());
    }

    public LoaderImageNet(int imageOnLoading, int iamgeForEmptyUri, boolean isCacheInMemory, boolean isCacheOnDisc) {
        this(imageOnLoading, iamgeForEmptyUri, isCacheInMemory, isCacheOnDisc, new SimpleBitmapDisplayer());
    }


    /**
     * @param imageOnLoading   图片在下载期间显示的图片
     * @param iamgeForEmptyUri 图片Uri为空或是错误的时候显示的图片
     * @param isCacheOnDisc    下载的图片是否缓存在内存中
     * @param isCacheOnDisc    下载的图片是否缓存在SD卡中
     * @param bitmapDisplayer  RoundedBitmapDisplayer（int roundPixels）设置圆角图片
     *                         FakeBitmapDisplayer（）这个类什么都没做
     *                         FadeInBitmapDisplayer（int durationMillis）设置图片渐显的时间
     *                         　　　　　　　          SimpleBitmapDisplayer()正常显示一张图片
     */
    public LoaderImageNet(int imageOnLoading, int iamgeForEmptyUri, boolean isCacheInMemory, boolean isCacheOnDisc, BitmapDisplayer bitmapDisplayer) {

        setDisplayImageOptions(imageOnLoading, iamgeForEmptyUri, isCacheInMemory, isCacheOnDisc, bitmapDisplayer);
    }


    /**
     * 核心方法
     *
     * @param imageUrl  图片url
     * @param imageView 图片 imageview控件
     */

    public void loaderImage(String imageUrl, ImageView imageView) {

        mImageLoader.displayImage(imageUrl, imageView, options); // imageUrl代表图片的URL地址，imageView代表承载图片的IMAGEVIEW控件

    }

    /**
     * @param imageUrl 给定网络路径，下载图片
     * @param listener p层实现ImageLoadingListener 听监下载完后回调方法，获取到图片
     */
    public void loaderImage(String imageUrl, ImageLoadingListener listener) {
        mImageLoader.loadImage(imageUrl, listener);

    }

    private void setDisplayImageOptions(int imageOnLoading, int iamgeForEmptyUri, boolean isCacheInMemory, boolean isCacheOnDisc, BitmapDisplayer bitmapDisplayer) {

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(imageOnLoading) // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(iamgeForEmptyUri)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(iamgeForEmptyUri) // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(isCacheInMemory)// 设置下载的图片是否缓存在内存中
                .cacheOnDisc(isCacheOnDisc)// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                // .delayBeforeLoading(int delayInMillis)//int
                // delayInMillis为你设置的下载前的延迟时间
                // 设置图片加入缓存前，对bitmap进行设置
                // .preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .displayer(bitmapDisplayer)// 是否设置为圆角，弧度为多少
                .build();// 构建完成
    }

    //清楚图片缓存（内存）
    public void clearMemoryCache() {
        mImageLoader.clearMemoryCache();
    }

    //清除图片缓存（）
    public void clearDiscCache() {
        mImageLoader.clearDiscCache();
    }

}
