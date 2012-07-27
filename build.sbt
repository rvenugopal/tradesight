name := "tradesight"

organization := "com.novus"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.2"

libraryDependencies := Seq(
  "joda-time" % "joda-time" % "2.1",
  "org.joda" % "joda-convert" % "1.2",
  "net.databinder" %% "unfiltered-jetty" % "0.6.3",
  "net.databinder" %% "unfiltered-filter" % "0.6.3",
  "net.databinder" %% "unfiltered-json" % "0.6.3",
  "org.specs2" % "specs2_2.9.2" % "1.11" % "test"
)

initialCommands := "import com.novus.tradesight._"
