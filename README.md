# scalatags-rx [![Build Status](https://travis-ci.org/rtimush/scalatags-rx.svg?branch=master)](https://travis-ci.org/rtimush/scalatags-rx)
ScalaTags-Rx is a small integration layer between [ScalaTags](https://github.com/lihaoyi/scalatags)
and [Scala.Rx](https://github.com/lihaoyi/scala.rx). It provides a set of type class instances for `Rx[T]` values
that allows you to use them directly in a ScalaTags DSL with changes automatically propagated to the resulting DOM:
```scala
object Example extends JSApp {

  val c = Var("blue")
  val text = Rx(s"It is a ${c()} text!")

  def toggle(): Unit = {
    c() = if (c() == "blue") "green" else "blue"
  }

  @JSExport
  override def main(): Unit = {
    document.body.appendChild(
      div(
        color := c, onclick := toggle _,
        text
      ).render
    )
  }

}
```

Getting Started
===============
ScalaTags-Rx is hosted on [Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cscalatags-rx),
to get started, simply add the following to your `build.sbt`:

```scala
resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "com.timushev" %% "scalatags-rx" % "0.1.0-SNAPSHOT"
```

At this moment there are no released stable artifacts, only a snapshot ones.
