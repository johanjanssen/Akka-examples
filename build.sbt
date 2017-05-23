import sbt.addCommandAlias

name := "akka-example"
version := "1.0"

lazy val akkaVersion = "2.4.18"
lazy val akkaStreamVersion = "2.0.1"

scalaVersion := "2.11.11"

lazy val commonSettings = Seq(
  scalaVersion := "2.11.11",
  libraryDependencies ++= Seq(
    "com.typesafe.akka" % "akka-actor_2.11" % akkaVersion
  )
)

lazy val clusterDependencies = Seq(
  "com.typesafe.akka" % "akka-cluster_2.11" % akkaVersion,
  "com.typesafe.akka" % "akka-cluster-tools_2.11" % akkaVersion
)

lazy val persistenceDependencies = Seq(
  "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
  "org.iq80.leveldb" % "leveldb" % "0.7",
  "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8"
)

lazy val akkaHttpDependencies = Seq(
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamVersion,
  "com.typesafe.akka" %% "akka-stream-experimental" % akkaStreamVersion,
  "com.typesafe.akka" %% "akka-http-core-experimental"  % akkaStreamVersion,
  "com.typesafe.akka" %% "akka-http-experimental" % akkaStreamVersion
)


lazy val root = (project in file("."))

lazy val `simple-actor` = project
  .settings(commonSettings,
    name := "simple-actor"
  )

lazy val `two-actors` = project
  .settings(commonSettings,
    name := "two-actors"
  )

lazy val `akka-cluster` = project
  .settings(commonSettings,
    name := "akka-cluster",
    libraryDependencies ++= clusterDependencies
  )

lazy val `akka-cluster-user` = project
  .settings(commonSettings,
    name := "akka-cluster-user",
    libraryDependencies ++= clusterDependencies
  )

lazy val `akka-cluster-with-router` = project
  .settings(commonSettings,
    name := "akka-cluster-with-router",
    libraryDependencies ++= clusterDependencies
  )

lazy val `akka-cluster-with-router-simple` = project
  .settings(commonSettings,
    name := "akka-cluster-with-router-simple",
    libraryDependencies ++= clusterDependencies
  )

lazy val `akka-cluster-with-router-user` = project
  .settings(commonSettings,
    name := "akka-cluster-with-router-user",
    libraryDependencies ++= clusterDependencies
  )

lazy val `akka-cluster-with-router-user-simple` = project
  .settings(commonSettings,
    name := "akka-cluster-with-router-user-simple",
    libraryDependencies ++= clusterDependencies
  )

lazy val `akka-cluster-singleton` = project
  .settings(commonSettings,
    name := "akka-cluster-singleton",
    libraryDependencies ++= clusterDependencies
  )

lazy val `akka-cluster-singleton-simple` = project
  .settings(commonSettings,
    name := "akka-cluster-singleton-simple",
    libraryDependencies ++= clusterDependencies
  )

lazy val `akka-cluster-singleton-user` = project
  .settings(commonSettings,
    name := "akka-cluster-singleton-user",
    libraryDependencies ++= clusterDependencies
  )

lazy val `akka-cluster-singleton-user-simple` = project
  .settings(commonSettings,
    name := "akka-cluster-singleton-user-simple",
    libraryDependencies ++= clusterDependencies
  )

lazy val `akka-cluster-with-router-and-cluster-singleton` = project
  .settings(commonSettings,
    name := "akka-cluster-with-router-and-cluster-singleton",
    libraryDependencies ++= clusterDependencies
  )

lazy val `akka-cluster-with-router-and-cluster-singleton-user` = project
  .settings(commonSettings,
    name := "akka-cluster-with-router-and-cluster-singleton-user"
  )

lazy val `akka-cluster-sharding` = project
  .settings(commonSettings,
    name := "akka-cluster-sharding",
    libraryDependencies ++= persistenceDependencies,
    libraryDependencies ++= clusterDependencies,
    libraryDependencies += "com.typesafe.akka" % "akka-cluster-sharding_2.11" % akkaVersion
  )


lazy val `akka-cluster-sharding-user` = project
  .settings(commonSettings,
    name := "akka-cluster-sharding-user",
    libraryDependencies ++= persistenceDependencies,
    libraryDependencies ++= clusterDependencies,
    libraryDependencies += "com.typesafe.akka" % "akka-cluster-sharding_2.11" % akkaVersion
  )

lazy val `akka-http` = project
  .settings(commonSettings,
    name := "akka-http",
    libraryDependencies ++= akkaHttpDependencies
  )

lazy val `akka-http-fat-jar` = project
  .settings(commonSettings,
    name := "akka-http-fat-jar",
    libraryDependencies ++= akkaHttpDependencies
  )

lazy val `fsm` = project
  .settings(commonSettings,
    name := "fsm"
  )

lazy val `persistence` = project
  .settings(commonSettings,
    name := "persistence",
    libraryDependencies ++= persistenceDependencies
  )

lazy val `persistence-with-snapshots` = project
  .settings(commonSettings,
    name := "persistence-with-snapshots",
    libraryDependencies ++= persistenceDependencies
  )

lazy val `scheduler` = project
  .settings(commonSettings,
    name := "scheduler"
  )

lazy val `scheduler-with-quartz` = project
  .settings(commonSettings,
    name := "scheduler-with-quartz",
    libraryDependencies += "com.enragedginger" %% "akka-quartz-scheduler" % "1.4.0-akka-2.3.x"
  )

lazy val `two-actors-code-quality` = project
  .settings(commonSettings,
    name := "two-actors-code-quality",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.2.6" % "test",
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
    ),
    scalacOptions ++= Seq(
      "-unchecked", "-deprecation", "-feature", "-Xlint", "-Yno-adapted-args", "-Ywarn-dead-code",
      "-Ywarn-numeric-widen", "-Ywarn-value-discard", "-Xfuture", "-Ywarn-unused-import",
      "-Ywarn-unused"
    ),
    coverageMinimum := 25,
    coverageFailOnMinimum := true,
    wartremoverWarnings ++= Warts.all,
    scapegoatVersion := "1.3.0",
    addCommandAlias("tests", ";clean ;coverage ;test ;coverageReport"),
    addCommandAlias("static", ";scalastyle ;scapegoat ;stats")
    //scalastyleConfig := baseDirectory.value / "project" / "scalastyle-config.xml"
  )

lazy val `two-actors-kamon-console-log` = project
  .settings(commonSettings,
    name := "two-actors-kamon-console-log",
    libraryDependencies ++= {
      val kamonVersion = "0.5.2"

      Seq(
        "io.kamon" %% "kamon-core" % kamonVersion,
        "io.kamon" %% "kamon-akka" % kamonVersion,
        "io.kamon" %% "kamon-log-reporter" % kamonVersion,
        "io.kamon" %% "kamon-system-metrics" % kamonVersion,
        "org.aspectj" % "aspectjweaver" % "1.8.5",
        "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
        "ch.qos.logback" % "logback-classic" % "1.0.13"
      )
    },
    aspectjSettings,
    javaOptions <++= AspectjKeys.weaverOptions in Aspectj
  )

lazy val `remote-actors-messageprotocol` = project
  .settings(commonSettings,
    name := "remote-actors-messageprotocol"
  )

lazy val `remote-actors-worker` = project
  .settings(commonSettings,
    name := "remote-actors-worker"
  ).dependsOn(`remote-actors-messageprotocol`)

lazy val `remote-actors-coordinator` = project
  .settings(commonSettings,
    name := "remote-actors-coordinator"
  ).dependsOn(`remote-actors-messageprotocol`)
