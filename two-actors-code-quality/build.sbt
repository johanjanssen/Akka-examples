name := "ExampleApplication"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

  libraryDependencies ++= {

  val akkaVersion = "2.4.1"

  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "org.scalatest" %% "scalatest" % "2.2.6" % "test",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
  )
}

coverageMinimum := 25
coverageFailOnMinimum := true

wartremoverWarnings ++= Warts.all

scapegoatVersion := "1.2.1"

//scalacOptions += "-unchecked -deprecation -feature -Xlint -Yno-adapted-args -Ywarn-dead-code -Ywarn-numeric-widen -Ywarn-value-discard -Xfuture -Ywarn-unused-import -Ywarn-unused"
//scalacOptions += "-unchecked; -deprecation"
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xlint", "-Yno-adapted-args", "-Ywarn-dead-code", "-Ywarn-numeric-widen", "-Ywarn-value-discard", "-Xfuture", "-Ywarn-unused-import", "-Ywarn-unused")

addCommandAlias("tests", ";clean ;coverage ;test ;coverageReport")
addCommandAlias("static", ";scalastyle ;scapegoat ;stats")

// Use 'sbt compile' to run Wartremover for static code analysis
// Use 'sbt tests' to run the tests and generate a report in /target/scala-2.11/scoverage-report
// Use 'sbt static' to run Scalastyle, Scapegoat for static code analysis and sbt-stats to calculate files, lines and characters
