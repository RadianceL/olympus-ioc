package com.example.config;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 配置文件解析器 -- JSON
 */
public class ResourceParser {

    private static String EL_JSON = "";

    static {
        final InputStream is = ResourceParser.class.getClassLoader().getResourceAsStream("el.json");
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[10];
        int length;
        try {
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            EL_JSON = result.toString(StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
