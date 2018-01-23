package com.github.huyisen.streaming.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  *
  * nc -lk 9999
  *
  *
  * Author: huyisen@cvte.com
  * Date: 2018-01-23
  * Copyright © 2018 CVTE. All Rights Reserved.
  */
object WordCountStart {

  def main(args: Array[String]): Unit = {


    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("wordcountonline")

    val ssc = new StreamingContext(conf, Seconds(10))
    val lines = ssc.socketTextStream("localhost", 9999, StorageLevel.MEMORY_AND_DISK)
    val wordCount = lines
      .flatMap(it => it.split(" "))
      .map(it => (it, 1))
      .reduceByKey(_ + _)

    wordCount.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
