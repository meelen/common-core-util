package org.meelen.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import junit.framework.TestCase;

public class TimeUtilTest extends TestCase {
	/** 东八区  */
	private static final ZoneOffset EAST_8 = ZoneOffset.of("+8");
	public void testInstant() {
		Instant.now();
	}
	
	public void testLocalDateTime() {
		//获取秒数
		Long seconds = LocalDateTime.now().toEpochSecond(EAST_8);
		System.out.println(seconds);
	}
}
