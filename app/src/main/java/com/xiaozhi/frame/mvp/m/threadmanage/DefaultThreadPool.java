package com.xiaozhi.frame.mvp.m.threadmanage;

import android.content.Context;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池 、缓冲队列
 */
public class DefaultThreadPool {

    private final int NET_WORK_2G = 2;
    private final int NET_WORK_3G_4G = 4;
    private final int NET_WORK_WIFI = 5;

    private final int BLOCKING_QUEUE_SIZE = 20;  // 阻塞队列最大任务数量

    private int poolSize;//线程池的大小最好设置成为CUP核数的2N
    private int poolMaxSize;// 设置线程池的最大线程数

    private Long keepAliveTime;//线程池维护线程所允许的空闲时间
    /**
     * 缓冲BaseRequest任务队列
     */
    private ArrayBlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(
            BLOCKING_QUEUE_SIZE);

    private static DefaultThreadPool defaultThreadPool = null;
    /**
     * 线程池
     */
    private ThreadPoolExecutor pool;

    private DefaultThreadPool() {
        poolSize = NET_WORK_WIFI;
        poolMaxSize = 2 * poolSize;
        keepAliveTime = 10L;
        pool = new ThreadPoolExecutor(poolSize, poolMaxSize, keepAliveTime, TimeUnit.SECONDS, blockingQueue, new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    private void setCorePoolSize(int corePoolSize) {
        if (pool != null) {
            pool.setCorePoolSize(corePoolSize);
        }
    }

    private void setMaximumPoolSize(int maximumPoolSize) {
        if (pool != null) {
            pool.setMaximumPoolSize(maximumPoolSize);
        }

    }

    /**
     * 分配线程数量
     */
    public void confirmPoolSize(Context context) {

        if (context == null) {
            poolSize = NET_WORK_WIFI;
            return;
        }

        switch (NetworkType.GetNetworkType(context)) {
            case NetworkType.NET_WORK_2G:
                poolSize = NET_WORK_2G;
                break;
            case NetworkType.NET_WORK_3G:
            case NetworkType.NET_WORK_4G:
                poolSize = NET_WORK_3G_4G;
                break;
            case NetworkType.NET_WORK_WIFI:
                poolSize = NET_WORK_WIFI;
                break;

            default:
                poolSize = NET_WORK_WIFI;
                break;
        }
        setCorePoolSize(poolSize);

        poolMaxSize = 2 * poolSize;

        setMaximumPoolSize(poolMaxSize);
    }

    public static synchronized DefaultThreadPool getInstance() {

        if (defaultThreadPool == null) {
            defaultThreadPool = new DefaultThreadPool();
        }

        return defaultThreadPool;
    }


    public void setKeepAliveTime(long time, TimeUnit unit) {
        if (pool != null) {
            pool.setKeepAliveTime(time, unit);
        }
    }

    /**
     * 删除缓冲序列线程
     */
    public void removeAllTask() {
        blockingQueue.clear();
    }

    public void removeTaskFromQueue(final Object obj) {
        blockingQueue.remove(obj);
        pool.remove((Runnable) obj);
    }


    /**
     * 关闭，并等待任务执行完成，不接受新任务
     */
    public void shutdown() {
        if (pool != null) {
            pool.shutdown();
        }
    }

    /**
     * 关闭，立即关闭，并挂起所有正在执行的线程，不接受新任务
     */
    public void shutdownRightnow() {
        if (pool != null) {
            pool.shutdownNow();
            try {
                // 设置超时极短，强制关闭所有任务
                pool.awaitTermination(1,
                        TimeUnit.MICROSECONDS);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行任务
     *
     * @param r
     */
    public void execute(final Runnable r) {
        if (r != null) {
            try {
                pool.execute(r);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}
