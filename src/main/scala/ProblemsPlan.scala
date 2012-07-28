package com.novus.tradesight

import unfiltered.request._
import unfiltered.response._

/*
 * Notes
 * 
 * ReturnsIndex is defined in the package object as:
 *   type ReturnsIndex = String => Option[SortedMap[DateTime, Double]]
 *
 * Use this to access the return streams of the stocks.
 */
object ProblemsPlan {
  def apply(returnStreams: ReturnsIndex) =
    unfiltered.filter.Planify {
      case GET(Path("/weighted")) =>
        val streams =
          NamedStream("PLEASE IMPLEMENT ME", (0 to 99).map { i =>
            org.joda.time.DateTime.now -> i.toDouble
          }.map(dataPoint)) :: Nil
        Json(anyJson(streams))
    }
}
