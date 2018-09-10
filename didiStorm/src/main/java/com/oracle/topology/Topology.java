package com.oracle.topology;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import com.oracle.bolt.CountBolt;
import com.oracle.bolt.DataParseBolt;
import com.oracle.spout.Spout;

/**
 * @author Clarinet
 * @since JDK8
 */
public class Topology {

    public static void main(String[] args) {
        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout("Spout",new Spout());

        topologyBuilder.setBolt("DataParseBolt",new DataParseBolt(),1)
                .shuffleGrouping("Spout");

        topologyBuilder.setBolt("CountBolt",new CountBolt(),1)
                .shuffleGrouping("DataParseBolt");


        Config config = new Config();

        config.put(Config.TOPOLOGY_ACKER_EXECUTORS, 1);
        config.setDebug(false);

        config.setNumWorkers(2);

        config.setMaxSpoutPending(50);


        StormTopology stormTopology = topologyBuilder.createTopology();

//        LocalCluster localCluster = new LocalCluster();

//        Cluster cluster = new Cluster();

        try {
            StormSubmitter.submitTopology("didi",config,stormTopology);
        } catch (AlreadyAliveException e) {
            e.printStackTrace();
        } catch (InvalidTopologyException e) {
            e.printStackTrace();
        }

//        localCluster.submitTopology("didi",config,stormTopology);


    }
}
