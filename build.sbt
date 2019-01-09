import com.typesafe.sbt.SbtGit.git._

organization := "com.timushev"
name := "scalatags-rx"
version := "0.3.0"

version := {
  (version.value, gitCurrentTags.value) match {
    case (v, w :: Nil) if s"v$v" == w => v
    case (v, Nil) => s"$v-SNAPSHOT"
    case _ => fail("Version and tag do not match")
  }
}

crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.1")
scalaVersion := "2.12.1"

def scalaRxVersion(scalaVersion: String): String =
    CrossVersion.partialVersion(scalaVersion) match {
      case Some((2, 10)) => "0.3.2"
      case _ => "0.4.0"
    }

libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "scalarx" % scalaRxVersion(scalaVersion.value),
  "com.lihaoyi" %%% "scalatags" % "0.6.2",
  "com.lihaoyi" %%% "utest" % "0.4.4" % "test"
)

testFrameworks += new TestFramework("utest.runner.Framework")

requiresDOM := true

lazy val `scalatags-rx` = project in file(".") enablePlugins ScalaJSPlugin