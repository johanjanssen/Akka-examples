name := "AkkaClusterShardingUser"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= {
  val akkaVersion = "2.4.1"

  Seq(
    "com.typesafe.akka" % "akka-actor_2.11" % akkaVersion,
    "com.typesafe.akka" % "akka-cluster_2.11" % akkaVersion,
    "com.typesafe.akka" % "akka-cluster-sharding_2.11" % akkaVersion,


    // Persistence
    "com.typesafe.akka" % "akka-persistence_2.11" % akkaVersion,
    "org.iq80.leveldb"            % "leveldb"          % "0.7",
    "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"
  )
}
