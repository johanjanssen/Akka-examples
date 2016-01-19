name := "ExampleApplication"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= {
  val akkaVersion = "2.4.1"
	val kamonVersion = "0.5.2"

  Seq(
		"com.typesafe.akka" %% "akka-actor" % akkaVersion,
		"io.kamon" %% "kamon-core" % kamonVersion,
		"io.kamon" %% "kamon-akka" % kamonVersion,
		"io.kamon" %% "kamon-log-reporter" % kamonVersion,
		"io.kamon" %% "kamon-system-metrics" % kamonVersion,
		"org.aspectj" % "aspectjweaver" % "1.8.5",
		"com.typesafe.akka" %% "akka-slf4j" % "2.4.1",
		"ch.qos.logback" % "logback-classic" % "1.0.13"
  )
}

// Bring the sbt-aspectj settings into this build
aspectjSettings

// Here we are effectively adding the `-javaagent` JVM startup
// option with the location of the AspectJ Weaver provided by
// the sbt-aspectj plugin.
//javaOptions in run <++= AspectjKeys.weaverOptions in Aspectj

// We need to ensure that the JVM is forked for the
// AspectJ Weaver to kick in properly and do it's magic.
//fork in run := true

javaOptions <++= AspectjKeys.weaverOptions in Aspectj

fork in run := true
