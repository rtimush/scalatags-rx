# scalatags-rx
ScalaTags-Rx is a small integration layer between [ScalaTags](https://github.com/lihaoyi/scalatags)
and [Scala.Rx](https://github.com/lihaoyi/scala.rx). It provides a set of type class instances for `Rx[T]` values
that allows you to use them directly in a ScalaTags DSL with changes automatically propagated to the resulting DOM:
```scala
object Example extends JSApp {

  val c = Var("blue")
  val text = Rx(s"It is a ${c()} text!")

  @JSExport
  def toggle(): Unit = {
    c() = if (c() == "blue") "green" else "blue"
  }

  @JSExport
  override def main(): Unit = {
    document.body.appendChild(
      div(
        color := c, onclick := "scalatags.rx.Example().toggle()",
        text
      ).render
    )
  }
  
}
```
