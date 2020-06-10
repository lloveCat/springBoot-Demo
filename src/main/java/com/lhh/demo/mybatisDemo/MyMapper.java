package com.lhh.demo.mybatisDemo;

import org.apache.ibatis.annotations.Insert;

/**
 * Created by lai.huihui on 2020/6/10.
 */
public interface MyMapper {
//    @Insert("insert into loginrecord(userId, loginTime) values('hahaha,this is testUserId', '2020-06-10 09:53:00')")
    @MyInsertAnno("insert into loginrecord(userId, loginTime) values('hahaha,this is testUserId', '2020-06-10 09:53:00')")
    int insert();
    @MyUpdateAnno("update loginrecord set logintime = '2020-06-10 09:53:00' where id = 18")
    int update();
    @MyDelAnno("delete loginrecord where id = 45")
    int delete();
    @MyQueryAnno("select * from loginrecord where id = 46")
    void select();
    @MyQueryAnno("select * from loginrecord limit 1,4")
    void selectList();
}
