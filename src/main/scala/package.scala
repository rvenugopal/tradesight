package com.novus

import scala.util.control.Exception.allCatch
import scala.collection.SortedMap
import org.joda.time.DateTime
import net.liftweb.json.Extraction.decompose
import net.liftweb.json.JsonAST.JValue

package object tradesight {
  
  type ReturnsIndex = String => Option[SortedMap[DateTime, Double]]
  
  type LineParser = String => Option[(DateTime, Double)]

  /** An Ordering for DateTime for supporting SortedMap keys. */
  implicit val dateTimeOrdering = new Ordering[DateTime] {
    override def compare(d1: DateTime, d2: DateTime) =
      d1.compareTo(d2)
  }

  /** Simple default line parser. */
  implicit val defaultParse = (line: String) => allCatch.opt {
    val Array(date, value) = line.split(",")
    val ymd = date.split(" ")(0)
    new DateTime(ymd) -> value.toDouble
  }
  
  /* Supporting model classes for chart data. */
  case class DataPoint[X, Y](x: X, y: Y)
  case class NamedStream[X, Y](key: String, values: Seq[DataPoint[X, Y]])
  
  /* Common utilities. */
  val anyJson: Any => JValue = decompose(_:Any)(net.liftweb.json.DefaultFormats)
  def dataPoint(pair: (DateTime, Double)) = DataPoint(pair._1.getMillis, pair._2)

}
