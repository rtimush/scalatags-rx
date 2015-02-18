organization := "com.timushev"
name := "scalatags-rx"
version := "0.1.0-SNAPSHOT"

crossScalaVersions := Seq("2.10.4", "2.11.5")
scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "scalarx" % "0.2.7",
  "com.lihaoyi" %%% "scalatags" % "0.4.5",
  "com.lihaoyi" %%% "utest" % "0.3.0" % "test"
)

testFrameworks += new TestFramework("utest.runner.Framework")

requiresDOM := true

lazy val `scalatags-rx` = project in file(".") enablePlugins ScalaJSPlugin
