package com.lhh.demo.MySpringMVCDemo;

/**
 * Created by lai.huihui on 2020/6/10.
 */
@MyServiceAnno("myServiceImpl")
public class MyServiceImpl implements MyService {
    @MyQualifer("myDao")
    private MyDao myDao;
    @Override
    public void test() {
        myDao.test();
    }
}
