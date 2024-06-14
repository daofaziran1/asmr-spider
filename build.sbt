
val pekkoVersion = "1.0.2"
val logbackVersion = "1.2.13"
val pekkoHttpVersion = "1.0.1"

lazy val `asmr-spider` = project
  .in(file("."))
  .settings(
    organization := "org.apache.pekko",
    scalaVersion := "3.4.2",
    Compile / scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint"),
    Compile / javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation"),
    run / javaOptions ++= Seq("-Xms128m", "-Xmx1024m", "-Djava.library.path=./target/native"),
    libraryDependencies ++= Seq(
      "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
      "org.apache.pekko" %% "pekko-cluster-typed" % pekkoVersion,
      "org.apache.pekko" %% "pekko-serialization-jackson" % pekkoVersion,
      "org.apache.pekko" %% "pekko-http" % pekkoHttpVersion,
      "ch.qos.logback" % "logback-classic" % logbackVersion,
      "org.apache.pekko" %% "pekko-multi-node-testkit" % pekkoVersion % Test,
      "org.scalatest" %% "scalatest" % "3.2.18" % Test,
      "org.apache.pekko" %% "pekko-actor-testkit-typed" % pekkoVersion % Test),
    run / fork := false,
    Global / cancelable := false,
    // disable parallel tests
    Test / parallelExecution := false,
    licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0"))))
  .configs(MultiJvm)
