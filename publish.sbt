import scala.xml.transform.{RewriteRule, RuleTransformer}
import scala.xml.{Node => XmlNode, NodeSeq => XmlNodeSeq, _}

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

// workaround for sbt/sbt#1834
pomPostProcess := { (node: XmlNode) =>
  new RuleTransformer(new RewriteRule {
    override def transform(node: XmlNode): XmlNodeSeq = node match {
      case e: Elem
        if e.label == "developers" =>
        <developers>
          {developers.value.map { dev =>
          <developer>
            <id>{dev.id}</id>
            <name>{dev.name}</name>
            <email>{dev.email}</email>
            <url>{dev.url}</url>
          </developer>
        }}
        </developers>
      case _ => node
    }
  }).transform(node).head
}
