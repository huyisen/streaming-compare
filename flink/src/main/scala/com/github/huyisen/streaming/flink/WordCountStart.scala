package com.github.huyisen.streaming.flink

import org.apache.flink.api.scala.{ExecutionEnvironment, _}
import org.apache.flink.core.fs.FileSystem.WriteMode

/**
  *
  * Author: huyisen@cvte.com
  * Date: 2018-01-22
  * Copyright © 2018 CVTE. All Rights Reserved.
  */
object WordCountStart {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)

    //从本地读取文件
    val text = env.readTextFile("/Users/cvter/IdeaProjects/cvte-bigdata/streaming-compare/flink/src/main/resources/wordCount.txt")

    //单词统计
    val counts = text
      .flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map { (_, 1) }
      .groupBy(0)
      .sum(1)

    //输出结果
    counts.print()

    counts.writeAsText("./flink/output.txt", WriteMode.OVERWRITE)



    env.execute("Scala WordCount Example")
  }

}
