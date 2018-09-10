package com.oracle.consumer;

/**
 * @author Clarinet
 * @since JDK8
 */
import com.oracle.util.PropertyUtil;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class KafkaConsumer {

    BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);

    public KafkaConsumer(){
        new Thread("kafka"){
            @Override
            public void run(){
                Properties props = new Properties();
                props.put("zookeeper.connect", PropertyUtil.getProperty("kafka_zookeeper"));
                props.put("zk.connectiontimeout.ms", "100000");
                props.put("group.id", PropertyUtil.getProperty("kafkaconsumer_group"));
                props.put("auto.offset.reset", "smallest");

                ConsumerConfig config = new ConsumerConfig(props);

                ConsumerConnector conn = Consumer.createJavaConsumerConnector(config);

                Map<String,Integer> map = new HashMap<String,Integer>();
                map.put(PropertyUtil.getProperty("topic"),1);
                Map<String, List<KafkaStream<byte[], byte[]>>> messageMap = conn.createMessageStreams(map);
                List<KafkaStream<byte[], byte[]>> list = messageMap.get(PropertyUtil.getProperty("topic"));

                for(KafkaStream<byte[],byte[]> streams : list){
                    for(MessageAndMetadata<byte[],byte[]> stream:streams){
                        String message = new String(stream.message());
                        try{
                            queue.put(message);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    public String getData(){
        return queue.poll();
    }
}
