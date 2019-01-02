package com.example.main;

import com.example.application.impl.ClassPathJsonApplicationContext;


/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 主程序入口
 */
public class Main {

    public static void main(String[] args) {
        final ClassPathJsonApplicationContext classPathJsonApplicationContext = new ClassPathJsonApplicationContext("el-default.json");

    }

}