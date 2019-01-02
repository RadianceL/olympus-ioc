package com.example.main;

import com.example.config.ResourceParser;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 主程序入口
 */
public class Main {



    public static void main(String[] args) {

        ResourceParser parser = new ResourceParser();
        final String s = parser.getClass().getName();
        final String[] split = s.split(".");

        System.out.println(split[0]);
    }

}