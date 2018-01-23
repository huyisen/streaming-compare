package com.github.huyisen.streaming.strom;

import org.apache.storm.shade.org.joda.time.DateTime;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * Author: huyisen@cvte.com
 * Date: 2018-01-22
 * Copyright © 2018 CVTE. All Rights Reserved.
 */
public class Show extends BaseRichBolt {

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

    }

    public void execute(Tuple tuple) {
//        最终的结果打印bolt
        System.out.println(new DateTime().toString("yyyy-MM-dd HH:mm:ss") + "  final bolt ");


        System.out.println(tuple.getStringByField("word") + "  " + tuple.getIntegerByField("count"));

        //实际应用中，最后一个阶段，大部分应该是持久化到mysql，redis，es，solr或mongodb中
    }
}
