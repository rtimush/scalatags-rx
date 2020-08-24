resolvers += Resolver.bintrayIvyRepo("rallyhealth", "sbt-plugins")

addSbtPlugin("com.eed3si9n" % "sbt-projectmatrix" % "0.6.0")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.1.1")
addSbtPlugin("com.rallyhealth.sbt" % "sbt-git-versioning" % "1.4.0")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.4")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "2.0.1")

libraryDependencies += "org.scala-js" %% "scalajs-env-jsdom-nodejs" % "1.1.0"
