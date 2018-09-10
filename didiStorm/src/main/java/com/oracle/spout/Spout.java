package com.oracle.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import com.oracle.consumer.KafkaConsumer;

import java.util.Map;

/**
 * @author Clarinet
 * @since JDK8
 *  Spout类,从Kafaka集群抽取过来的数据,用于tuple的发送
 */
public class Spout extends BaseRichSpout {

    public SpoutOutputCollector collector = null;

    public KafkaConsumer consumer = null;
    /*
        开启阶段,实际就是一个初始化的过程
     */
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = collector;

        consumer = new KafkaConsumer();
    }
    /*
        将tuple传给Bolt
     */
    public void nextTuple() {
        //从队列中拿出一条数据,作为一个tuple
        String message = consumer.getData();
        //如果消息不为空,就作为一个tuple往下传,调用collector的emit()方法
        if(null != message && !"".equals(message)){
            collector.emit(new Values(message));
        }
    }
    /*
        为每一个tuple加上一个头信息,方便回滚我认为
     */
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("metaData"));
    }
}
