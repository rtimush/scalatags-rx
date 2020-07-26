ThisBuild / licenses := Seq("BSD 3-Clause" -> url("https://github.com/rtimush/scalatags-rx/blob/master/LICENSE"))
ThisBuild / homepage := Some(url("https://github.com/rtimush/scalatags-rx"))
ThisBuild / developers := Developer("rtimush", "Roman Timushev", "rtimush@gmail.com", url("https://github.com/rtimush")) :: Nil
ThisBuild / scmInfo := Some(ScmInfo(
  url("https://github.com/rtimush/scalatags-rx"),
  "scm:git:https://github.com/rtimush/scalatags-rx.git",
  Some("scm:git:git@github.com:rtimush/scalatags-rx.git")
))
ThisBuild / publishMavenStyle := true
ThisBuild / publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
