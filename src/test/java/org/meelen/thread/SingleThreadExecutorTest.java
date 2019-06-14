package org.meelen.thread;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import junit.framework.TestCase;

public class SingleThreadExecutorTest extends TestCase{
	private static final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    
	/**
	 * SingleThreadExecutor：单个后台线程 , 其缓冲队列是无界的
	 * 
	 * public static ExecutorService newSingleThreadExecutor() {
     *      return new FinalizableDelegatedExecutorService
     *          (new ThreadPoolExecutor(1, 1,
     *                              0L, TimeUnit.MILLISECONDS,
     *                              new LinkedBlockingQueue<Runnable>()));
     * }
	 */
	public void testSingleThreadExecutor() {
		for(int i=0; i<10; i++) {
			final int x = i;
			singleThreadExecutor.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("test:" + x);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
