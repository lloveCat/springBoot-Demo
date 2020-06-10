package com.lhh.demo.mybatisDemo;

/**
 * Created by lai.huihui on 2020/6/10.
 */
public class MainClass {
    public static void main(String args[]) {
        MyMapper myMapper = MySqlSession.getMapper(MyMapper.class);
        int result =myMapper.insert();
        result = myMapper.delete();
        result = myMapper.update();
        myMapper.select();
        myMapper.selectList();
        System.out.println(result);
    }
}
