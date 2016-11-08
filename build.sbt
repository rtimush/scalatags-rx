import com.typesafe.sbt.SbtGit.git._

organization := "com.timushev"
name := "scalatags-rx"
version := "0.3.0"

version <<= (version, gitCurrentTags) apply {
  case (v, w :: Nil) if s"v$v" == w => v
  case (v, Nil) => s"$v-SNAPSHOT"
  case _ => fail("Version and tag do not match")
}

crossScalaVersions := Seq("2.10.5", "2.11.8")
scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "scalarx" % "0.3.2",
  "com.lihaoyi" %%% "scalatags" % "0.6.1",
  "com.lihaoyi" %%% "utest" % "0.3.1" % "test"
)

testFrameworks += new TestFramework("utest.runner.Framework")

requiresDOM := true

lazy val `scalatags-rx` = project in file(".") enablePlugins ScalaJSPlugin
