package org.meelen.collection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import junit.framework.TestCase;

public class LongSetTest extends TestCase{
	public void testLongSet() {
		int size = 10;
		LongSet userIds = new LongOpenHashSet();
		// 10000000000000000001 new BigInteger()
		long userId = Long.MAX_VALUE;
		for (int index = 0; index < size; index++) {
			userIds.add(userId--);
		}
		System.out.println("before set :" + userIds);
		// 创建对象并保存
		byte[] bytes = toByteArray(userIds);
		System.out.println("bytes size :" + bytes.length);
		userIds = toObject(bytes);
		System.out.println("after set :" + userIds);
	}
    
	
	/**
	 * 对象转数组
	 * 
	 * @param obj
	 * @return
	 */
	public byte[] toByteArray(LongSet obj) {
		// 括号里不用手动关闭
		try (
				// 64位系统中long类型占8字节=64bit
				ByteArrayOutputStream bos = new ByteArrayOutputStream(obj.size() * 8); 
				DataOutputStream writer = new DataOutputStream(bos); ) {
			
			for (long value : obj) {
				writer.writeLong(value);
			}
			writer.flush();
			byte[] bytes = bos.toByteArray();
			return bytes;
			
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 数组转对象
	 * 
	 * @param bytes
	 * @return
	 */
	public LongSet toObject(byte[] bytes) {
		LongSet obj = new LongOpenHashSet();
		// 括号里不用手动关闭
		try (
				ByteArrayInputStream bis = new ByteArrayInputStream(bytes); 
				DataInputStream ois = new DataInputStream(bis); ) {
			
			while (ois.available() > 0) {
				obj.add(ois.readLong());
			}
			return obj;
		} catch (IOException ex) {
			return null;
		}
	}
}
