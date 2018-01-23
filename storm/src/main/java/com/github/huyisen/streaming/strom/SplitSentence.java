package com.github.huyisen.streaming.strom;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Author: huyisen@cvte.com
 * Date: 2018-01-22
 * Copyright © 2018 CVTE. All Rights Reserved.
 */
public class SplitSentence extends BaseBasicBolt {

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String sentence = tuple.getString(0);
        String words[] = sentence.split(" ");
        for (String w : words) {
            basicOutputCollector.emit(new Values(w));
        }
    }
}
