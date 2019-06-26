package org.meelen.redisson;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.config.Config;

import junit.framework.TestCase;

public class RedissonTest extends TestCase{
	public Redisson redisson;
	public static final String KEY = "redisson.test.lockpoint";

	public RedissonTest(String testName) {
		super(testName);
	}
	
	@Override
	public void setUp() throws Exception{
		try{
			Config config = new Config();
//			String url = "redis://47.106.130.16:9379";
//			config.useSingleServer().setAddress(url);
//			config.useSingleServer().setPassword("2019foOU*129redAb");
			String url = "redis://47.112.0.183:9379";
			config.useSingleServer().setAddress(url);
			config.useSingleServer().setPassword("2019foOU*129redAb");
			// 自定义线程池
			int corePoolSize =  Runtime.getRuntime().availableProcessors();
			int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;
			long keepAliveTime = 30;
			TimeUnit unit = TimeUnit.MINUTES;
			BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10000); 
			ExecutorService executor = new ThreadPoolExecutor(
					corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
			config.setExecutor(executor);
			
			this.redisson  = (Redisson) Redisson.create(config);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
