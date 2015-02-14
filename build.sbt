name := "scalatags-rx"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "scalarx" % "0.2.7",
  "com.lihaoyi" %%% "scalatags" % "0.4.5",
  "com.lihaoyi" %%% "utest" % "0.3.0" % "test"
)

testFrameworks += new TestFramework("utest.runner.Framework")

requiresDOM := true

lazy val `scalatags-rx` = project in file(".") enablePlugins ScalaJSPlugin
