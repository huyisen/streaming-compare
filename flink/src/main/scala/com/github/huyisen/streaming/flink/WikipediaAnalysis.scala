package com.github.huyisen.streaming.flink

import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, _}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditsSource

/**
  *
  * Author: huyisen@cvte.com
  * Date: 2018-01-22
  * Copyright © 2018 CVTE. All Rights Reserved.
  */
object WikipediaAnalysis {

  def main(args: Array[String]): Unit = {

    val see = StreamExecutionEnvironment.getExecutionEnvironment
    val edits = see.addSource(new WikipediaEditsSource)

    val result = edits
      .keyBy(_.getUser)
      .map(it => (it.getUser, it.getByteDiff))
      .keyBy(_._1)
      .timeWindow(Time.seconds(5))
      .reduce((a, b) => (a._1, a._2 + b._2))
    result.print()

    //    result.addSink()

    see.execute()

  }

}
