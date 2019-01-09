import com.typesafe.sbt.SbtGit.git._

organization := "com.timushev"
name := "scalatags-rx"
version := "0.3.1"

version := {
  (version.value, gitCurrentTags.value) match {
    case (v, w :: Nil) if s"v$v" == w => v
    case (v, Nil) => s"$v-SNAPSHOT"
    case _ => fail("Version and tag do not match")
  }
}

crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.1")
scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "scalarx" % "0.4.0",
  "com.lihaoyi" %%% "scalatags" % "0.6.2",
  "com.lihaoyi" %%% "utest" % "0.4.4" % "test"
)

testFrameworks += new TestFramework("utest.runner.Framework")

requiresDOM := true

lazy val `scalatags-rx` = project in file(".") enablePlugins ScalaJSPlugin
