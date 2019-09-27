package org.meelen.jdk;

import junit.framework.TestCase;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：liangmin
 * @date ：Created in 2019/7/19 16:08
 * @description：
 * @modified By：
 */
public class TypeTest extends TestCase {
    // Duration线程安全 最实用是 between 方法，计算两个日期之间的天数或者小时数。
    public void testDuration(){
        Duration timeOut;

    }

    public void testArrays(){
        String nodesValue = "11,22,33";
        List<String> nodes = Arrays.asList(nodesValue.split(","));
        String[] nodesArr = nodes.toArray(new String[nodes.size()]);
    }
}
