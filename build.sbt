import Dependencies.compileDependencies

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val appMainClass = "ca.vgorcinschi.Main"

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= compileDependencies,
    name := "sbt-doc-macwire-issue",
    Compile / mainClass := Some(appMainClass)
  )
