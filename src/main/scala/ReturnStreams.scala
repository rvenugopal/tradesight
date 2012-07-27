package com.novus.tradesight

import scala.collection.SortedMap
import scala.io.Source
import java.io.File
import org.joda.time.DateTime

/** Utilities for loading return streams. */
object ReturnStreams {
  
  val empty = SortedMap.empty[DateTime, Double]

  /** Map an index of return streams from all files in a directory. */
  def mappedFromDir(name: String): Map[String, SortedMap[DateTime, Double]] =
    mappedFromFiles(new File(name).listFiles:_*)

  /** Map an index of return streams from a set of files using the file names as the keys. */
  def mappedFromFiles(names: File*)(implicit parse: LineParser): Map[String, SortedMap[DateTime, Double]] =
    names.foldLeft(Map.empty[String, SortedMap[DateTime, Double]]) {
      case (m, name) => m + (name.getName -> fromFile(name))
    }

  /** Read a return stream from file, given a line parsing function. */
  def fromFile(name: File)(implicit parse: LineParser): SortedMap[DateTime, Double] =
    fromLines(Source.fromFile(name).getLines)

  /** Yield a return stream from an iterator of strings, given a line parsing function. */
  def fromLines(lines: Iterator[String])(implicit parse: LineParser): SortedMap[DateTime, Double] =
    lines.take(100).foldLeft(empty) { // Note: take(100) is arbitrary to keep the points to a minimum on the front end.
      case (m, line) => parse(line).map(m + _).getOrElse(m)
    }

}
