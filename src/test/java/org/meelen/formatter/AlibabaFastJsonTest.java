package org.meelen.formatter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import junit.framework.TestCase;

public class AlibabaFastJsonTest extends TestCase {

    public static class User{
        @JSONField(name = "userName")
        private String name;
        private int age;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }

    /**
     * 转换字段名称-实体对象转json字符串
     */
    public void testConverFieldNameObjectToJsonString(){
        User user = new User();
        user.setAge(12);
        user.setName("zhangss");
        String userStr = JSON.toJSONString(user);
        System.out.println(userStr);
    }

    /**
     * 转换字段名称json字符串转实体对象
     */
    public void testConverFieldNameJsonStringToObject(){
        String userJson = "{\"userName\":\"zhangs\",\"age\":122}";
        User user = JSON.parseObject(userJson, User.class);
        System.out.println(user.getName());
    }


}
