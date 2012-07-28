package com.novus.tradesight

import unfiltered.jetty.Http
import unfiltered.request._
import unfiltered.response._

object App {
  def main(args: Array[String]) {
    
    val streamMap = ReturnStreams.mappedFromDir("data")

    val plan = unfiltered.filter.Planify {
      case GET(Path("/streams")) =>
        val streams = streamMap.keySet.foldLeft(List.empty[NamedStream[Long, Double]]) {
          (rs, sym) =>
            streamMap.get(sym).map { s =>
              val values = s.map(dataPoint).toSeq
              NamedStream(sym, values) :: rs
            } getOrElse(rs)
        }
        Json(anyJson(streams))
    }
    
    Http(8888)
      .context("/static")(_.resources(this.getClass.getResource("/static")))
      .plan(plan)
      .plan(ProblemsPlan(streamMap))
      .run()
  }
}
