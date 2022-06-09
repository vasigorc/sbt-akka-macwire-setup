import sbt._

object Dependencies {
  lazy val akkaVersion = "2.6.18"
  lazy val logbackVersion = "1.2.11"
  lazy val slf4jVersion = "1.7.36"
  lazy val macWireVersion = "2.5.7"
  lazy val opticsVersion = "3.1.0"

  val compileDependencies: Seq[ModuleID] = Seq(
    "ch.qos.logback" % "logback-classic" % logbackVersion,
    "ch.qos.logback" % "logback-core" % logbackVersion,
    "com.softwaremill.macwire" % "macros_2.13" % macWireVersion,
    "com.softwaremill.macwire" %% "macrosakka" % macWireVersion % "provided",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe" % "config" % "1.4.2",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
    "commons-io" % "commons-io" % "2.11.0",
    "dev.optics" %% "monocle-core" % opticsVersion,
    "dev.optics" %% "monocle-macro" % opticsVersion,
    "io.estatico" %% "newtype" % "0.4.4",
    "org.slf4j" % "slf4j-api" % "1.7.36",
    "org.typelevel" %% "cats-core" % "2.7.0"
  )
}
