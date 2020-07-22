package com.shlock.mptest;

import com.shlock.mptest.entity.User;
import com.shlock.mptest.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MptestApplicationTests {

    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){
        userMapper.selectList(null).forEach(System.out::println);
    }
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("李四");
        user.setAge(30);
        user.setEmail("lisi@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result); //影响的行数
        System.out.println(user); //id自动回填
    }

    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(1285927103291383810L);
        user.setAge(18);
        int result = userMapper.updateById(user);
        System.out.println(result);
    }
    @Test
    public void testUpdateByIdVersion(){
        User user = userMapper.selectById(1285927103291383810L);
        System.out.println(user);
        user.setVersion(1);
        user.setAge(100);
        int result = userMapper.updateById(user);
        System.out.println(result);
    }
    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }


}
