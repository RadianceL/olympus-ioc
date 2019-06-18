package com.olympus.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author eddie
 * @createTime 2018-12-27
 * @description 配置文件解析器 -- JSON
 */
public class ConfigLoader {

    private static JSONObject EL_JSON;

    public ConfigLoader(String configPath){
        final InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream(configPath);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[10];
        int length;
        try {
            if (!Objects.isNull(is)) {
                while ((length = is.read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }
            }
            final String str = result.toString("UTF-8");
            EL_JSON = JSONObject.parseObject(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBaseBeanPackage(){
        final String baseBeanPkg = EL_JSON.getString("base_bean_pkg");
        if (StringUtils.isBlank(baseBeanPkg)){
            throw new RuntimeException("");
        }
        return baseBeanPkg;
    }
}
