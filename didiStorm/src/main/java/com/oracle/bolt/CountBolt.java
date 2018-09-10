package com.oracle.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import com.oracle.util.SaveObject;
import com.oracle.vo.Didi;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Clarinet
 * @since JDK8
 */
public class CountBolt extends BaseBasicBolt {

    Map<String,Integer> orderCountMap = new HashMap<String,Integer>();
    Map<String, Integer> priceCountMap = new HashMap<String, Integer>();


    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        Map<String,String> map = (Map<String,String>)tuple.getValueByField("parsedData");
        if(null!=map && map.size()>0){
            String address = map.get("address");
            if(orderCountMap.containsKey(address)){
                orderCountMap.put(address,orderCountMap.get(address)+1);
                priceCountMap.put(address,priceCountMap.get(address)+Integer.parseInt(map.get("price")));
            }else{
                orderCountMap.put(address,1);
                priceCountMap.put(address,Integer.parseInt(map.get("price")));
            }

            Didi info = new Didi();
            info.setId(UUID.randomUUID().toString().toLowerCase());
            info.setPhoneNumber(map.get("phone"));
            info.setAddress(map.get("address"));
            info.setTime(map.get("time"));
            info.setDistance(Integer.parseInt(map.get("distance")));
            info.setPrice(Integer.parseInt(map.get("price")));
            info.setOrderCount(orderCountMap.get(map.get("address")));
            info.setPriceCount(priceCountMap.get("address"));

            SaveObject obj = new SaveObject();

            try {
                obj.saveObject(info);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            return;
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
