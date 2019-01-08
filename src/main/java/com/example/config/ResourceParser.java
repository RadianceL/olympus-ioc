package com.example.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 配置文件解析器 -- JSON
 */
public class ResourceParser {

    private static JSONObject EL_JSON;

    public ResourceParser(String configPath){
        final InputStream is = ResourceParser.class.getClassLoader().getResourceAsStream(configPath);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[10];
        int length;
        try {
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            final String str = result.toString(StandardCharsets.UTF_8);
            EL_JSON = JSONObject.parseObject(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBaseBeanPackage(){
        final String baseBeanPkg = EL_JSON.getString("base_bean_pkg");
        if (baseBeanPkg == null || baseBeanPkg.equals("")){
            throw new RuntimeException("");
        }
        return baseBeanPkg;
    }
}
