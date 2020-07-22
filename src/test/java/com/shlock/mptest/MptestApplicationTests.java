package com.shlock.mptest;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        user.setName("王五");
        user.setAge(100);
        user.setEmail("wangwu@qq.com");
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
    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(3,2);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }
    @Test
    public void testDeleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);
        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }
    @Test
    public void testLogicDelete() {
        int result = userMapper.deleteById(1285931547391332354L);
        System.out.println(result);
    }

    @Test
    public void testLogicDeleteSelect() {
        User user  = userMapper.selectById(1285931547391332354L);
        System.out.println("user = " + user);
    }

    @Test
    public void test2(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",20);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
    @Test
    public void test3(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",20);
        wrapper.lt("age",50);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test4(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","shlock");
        wrapper.ne("age",50);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
    @Test
    public void test5(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id","name","age");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

}
