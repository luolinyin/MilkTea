package com.xiaozhi.frame;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.xiaozhi.frame.mvp.m.threadmanage.DefaultThreadPool;

import java.io.File;

/**
 * Created by Fynner on 2016/12/7.
 */
public class BaseApplication extends Application {
    private Context context;

    // 单例模式向外界提供获取BaseApplication的方法
    private static BaseApplication mBaseApplication = null;

    public synchronized static BaseApplication getInstance() {
        if (mBaseApplication == null) {
            mBaseApplication = new BaseApplication();
        }
        return mBaseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        // （智构架）网络请求情况分配线程池 线程数
        DefaultThreadPool.getInstance().confirmPoolSize(context);
        // 初始化Image_Loader框架
        initImageLoader();

    }

    private void initImageLoader() {
        /**
         * 方式一：创建默认的ImageLoader配置参数
         */
        // UseDefaultConfig();// 默认
        // UseStandardConfig();// 标准
        /**
         * 方式二：创建自定义的ImageLoader配置参数
         */
        UseOwnConfig();// 高级
    }

    private void UseOwnConfig() {
        // 使用ImageLoader定义的缓存路径
//        File cacheDir = StorageUtils.getCacheDirectory(context);

        // 使用自定义缓存路径
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .memoryCacheExtraOptions(480, 800)
                // max width, max height，即保存的每个缓存文件的最大长宽

//                  .discCacheExtraOptions(480, 800, Bitmap.CompressFormat.PNG, 75, null)
                // Can slow ImageLoader, use it carefully (Better don't use
                // it)/设置缓存的详细信息，最好不要设置这个
                .threadPoolSize(3)
                // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                // 可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                // 将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100)
                // 缓存的文件数量

                .discCache(new UnlimitedDiskCache(cacheDir))
                // 自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(
                        new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout
                // (5
                // s),
                // readTimeout
                // (30
                // s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();// 开始构建
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    private void init() {
        context = getApplicationContext();
    }
}
