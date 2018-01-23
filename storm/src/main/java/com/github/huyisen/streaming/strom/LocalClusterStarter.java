package com.github.huyisen.streaming.strom;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import java.util.concurrent.TimeUnit;

/**
 * Author: huyisen@cvte.com
 * Date: 2018-01-22
 * Copyright © 2018 CVTE. All Rights Reserved.
 */
public class LocalClusterStarter {

    public static void main(String[] args) throws InterruptedException {

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new RandomSentenceSpout(), 1);
        builder.setBolt("split", new SplitSentence(), 1).shuffleGrouping("spout");
        builder.setBolt("count", new WordCount(), 1).fieldsGrouping("split", new Fields("word"));
        builder.setBolt("show", new Show(), 1).shuffleGrouping("count");


        Config conf = new Config();
        conf.setDebug(true);

        conf.setMaxTaskParallelism(3);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("word-count", conf, builder.createTopology());

        TimeUnit.SECONDS.sleep(120);
        cluster.shutdown();
    }
}
