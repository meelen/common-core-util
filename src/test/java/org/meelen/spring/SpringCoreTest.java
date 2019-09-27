package org.meelen.spring;

import junit.framework.TestCase;
import org.junit.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author ：liangmin
 * @date ：Created in 2019/7/19 15:55
 * @description：
 * @modified By：
 */
public class SpringCoreTest extends TestCase {
    public void testReflectionUtils(){
        Method method = ReflectionUtils.findMethod(SpringCoreTest.class, "isSsl");
        SpringCoreTest test = new SpringCoreTest();
        Boolean isSsl = (Boolean) ReflectionUtils.invokeMethod(method, test);
        Assert.assertTrue(isSsl);
    }
    public boolean isSsl(){
        return true;
    }


}
