package com.oracle.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.oracle.util.MapParseUtil;

import java.util.Map;

/**
 * @author Clarinet
 * @since JDK8
 */
public class DataParseBolt extends BaseBasicBolt {
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String message = tuple.getStringByField("metaData");
        Map<String,String> map = MapParseUtil.parseMap(message);
        basicOutputCollector.emit(new Values(map));
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("parsedData"));
    }
}
