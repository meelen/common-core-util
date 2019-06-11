package org.meelen.redisson;

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
			String url = "redis://47.106.130.16:9379";
			config.useSingleServer().setAddress(url);
			config.useSingleServer().setPassword("2019foOU*129redAb");
			this.redisson  = (Redisson) Redisson.create(config);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
