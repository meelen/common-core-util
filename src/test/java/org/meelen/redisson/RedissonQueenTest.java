package org.meelen.redisson;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RQueue;

import junit.framework.Test;
import junit.framework.TestSuite;

/**   
 * @ClassName:  RedissonQueenTest   
 * @Description:  方法以test开通可以省略@Test注解
 * @author: liangmin 
 * @date:   2019年6月11日 下午8:05:23   
 *     
 * @Copyright: 2019 www.jielema.com Inc. All rights reserved. 
 */
public class RedissonQueenTest extends RedissonTest{

	public static final String QUEEN_NAME = "redisson.test.queen";

	/**
	 * 
	 */
	public RedissonQueenTest(String testName){
		super(testName);
	}
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite(){
		return new TestSuite(RedissonQueenTest.class);
	}
	
	/**
	 * 写队列
	 */
	public void testAddQueen() {
		// 定义个队列
		RQueue<Long> queue = redisson.getQueue(QUEEN_NAME);
		queue.add(19099990998888L);
		queue.add(19099990998889L);
		queue.add(19099990998880L);
	}
	
	/**
	 * 读取队列
	 * peek()方法检查，或者使用poll()方法检查和删除。
	 * @throws IOException 
	 */
	public void testPollQueen() throws IOException {
		// 定时线程池 
		ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
		scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				RQueue<Long> queue = redisson.getQueue(QUEEN_NAME);
				Long userId = queue.poll();
				System.out.println(userId==null ? "---" : userId);
			}
			// 0表示首次执行任务的延迟时间，2表示每次执行任务的间隔时间，TimeUnit.MINUTES执行的时间间隔数值单位
		}, 0, 2, TimeUnit.SECONDS);
		System.in.read();
	}
	
}
