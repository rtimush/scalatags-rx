licenses := Seq("BSD 3-Clause" -> url("https://github.com/rtimush/scalatags-rx/blob/master/LICENSE"))

homepage := Some(url("https://github.com/rtimush/scalatags-rx"))

developers := Developer("rtimush", "Roman Timushev", "rtimush@gmail.com", url("https://github.com/rtimush")) :: Nil

scmInfo := Some(ScmInfo(
  url("https://github.com/rtimush/scalatags-rx"),
  "scm:git:https://github.com/rtimush/scalatags-rx.git",
  Some("scm:git:git@github.com:rtimush/scalatags-rx.git")
))

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

aetherPublishSettings
aetherPublishLocalSettings
