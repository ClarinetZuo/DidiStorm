package com.oracle.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Clarinet
 * @since JDK8
 *  将从Kafka集群抽取的数据,并由Spout发送来的数据,变成键值对的形式的工具类
 *  parseMap()方法很关键
 */
public class MapParseUtil {

    private static String[] columns = {"phone","address","time","distance","price"};

    private static HashMap<String,String> myMap = new HashMap<String, String>();

    public static Map<String,String> parseMap(String input){
        String[] inputs = input.split(",");

        if(input.length() != 5){
            return null;
        }
        for(int i=0; i<5; i++){
            myMap.put(columns[i],inputs[i]);
        }

        return myMap;

    }


}
