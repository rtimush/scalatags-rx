ThisBuild / organization := "com.timushev"
ThisBuild / gitVersioningSnapshotLowerBound := Some("0.5.0")
ThisBuild / publishTo := sonatypePublishToBundle.value

lazy val `scalatags-rx` = (projectMatrix in file("."))
  .enablePlugins(SemVerPlugin)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "scalatags-rx",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "scalarx" % "0.4.3",
      "com.lihaoyi" %%% "scalatags" % "0.9.1",
      "com.lihaoyi" %%% "utest" % "0.8.2" % "test"
    ),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
  )
  .jsPlatform(scalaVersions = Seq("2.12.13", "2.13.11"))

lazy val root = (project in file("."))
  .aggregate(`scalatags-rx`.projectRefs: _*)
  .settings(publish / skip := true, compile / skip := true)
