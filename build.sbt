import com.typesafe.sbt.SbtGit.git._

organization := "com.timushev"
name := "scalatags-rx"
version := "0.2.0"

version <<= (version, gitCurrentTags) apply {
  case (v, w :: Nil) if s"v$v" == w => v
  case (v, Nil) => s"$v-SNAPSHOT"
  case _ => fail("Version and tag do not match")
}

crossScalaVersions := Seq("2.10.5", "2.11.6")
scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "scalarx" % "0.2.8",
  "com.lihaoyi" %%% "scalatags" % "0.4.6",
  "com.lihaoyi" %%% "utest" % "0.3.1" % "test"
)

testFrameworks += new TestFramework("utest.runner.Framework")

requiresDOM := true

lazy val `scalatags-rx` = project in file(".") enablePlugins ScalaJSPlugin
