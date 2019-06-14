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
		// 日期转秒
		Long seconds = LocalDateTime.now().toEpochSecond(EAST_8);
		System.out.println(seconds);
		// 毫秒转日期
		Instant instant = Instant.ofEpochMilli(1560873600000L);
		System.out.println(LocalDateTime.ofInstant(instant, EAST_8));
	}
}
