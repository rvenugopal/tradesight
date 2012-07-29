package com.novus.tradesight

import unfiltered.request._
import unfiltered.response._
import org.joda.time.DateTime
import collection.SortedMap

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
        val stream = 
          NamedStream("PLEASE IMPLEMENT ME",
            weightedReturns(streamMap).map(dataPoint)
        )
        Json(anyJson(stream :: Nil))
    }

  def weightedReturns(streamMap: ReturnsIndex): SortedMap[DateTime, Double] =
    (0 to 99).foldLeft(SortedMap.empty[DateTime, Double]) { (acc, i) =>
      acc + (DateTime.now -> .1d)
    }

}
