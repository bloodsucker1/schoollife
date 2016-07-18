package com.example.work.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

	private static ThreadPool mThreadPool = null;

	public static ThreadPool getThreadPool() {
		if (mThreadPool == null) {
			synchronized (ThreadPoolManager.class) {
				if (mThreadPool == null) {
					// 获取cpu个数
					int cpuNum = Runtime.getRuntime().availableProcessors();
					// 线程数
					int count = cpuNum * 2 + 1;

					mThreadPool = new ThreadPool(count, count, 0);
				}
			}
		}
		return mThreadPool;
	}

	public static class ThreadPool {

		private int corePoolSize;// 核心线程数
		private int maximumPoolSize;// 最大线程数
		private int keepAliveTime;// 保持活跃的时间（休息时间）

		public ThreadPoolExecutor executor;

		public ThreadPool(int corePoolSize, int maximumPoolSize,
				int keepAliveTime) {
			this.corePoolSize = corePoolSize;
			this.maximumPoolSize = maximumPoolSize;
			this.keepAliveTime = keepAliveTime;
		}

		public void execute(Runnable r) {
			if (executor == null) {
				// 参1：核心线程数 参2：最大线程数 参3：保持活跃的时间（休息时间）参4：活跃时间的单位 参5：线程队列 参6：线程工厂
				// 参7：线程阻塞处理handler
				executor = new ThreadPoolExecutor(corePoolSize,
						maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
						new LinkedBlockingDeque<Runnable>(),
						Executors.defaultThreadFactory());
			}
			executor.execute(r);
		}
		
		//取消下载
		public void cancle(Runnable r){
			executor.getQueue().remove(r);
		}
	}
}
