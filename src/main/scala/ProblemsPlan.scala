package com.novus.tradesight

import unfiltered.request._
import unfiltered.response._

/*
 * Notes
 * 
 * ReturnsIndex is defined in the package object as:
 *   type ReturnsIndex = Map[String, collection.SortedMap[org.joda.time.DateTime, Double]]
 */
object ProblemsPlan {
  def apply(streamMap: ReturnsIndex) =
    unfiltered.filter.Planify {
      case GET(Path("/weighted")) =>
        val streams =
          NamedStream("PLEASE IMPLEMENT ME", (0 to 99).map { i =>
            org.joda.time.DateTime.now -> i.toDouble
          }.map(dataPoint)) :: Nil
        Json(anyJson(streams))
    }
}
