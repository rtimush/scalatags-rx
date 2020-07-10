package scalatags.rx

import org.scalajs.dom.document
import rx.Ctx.Owner.Unsafe._
import rx._
import scalatags.JsDom.all._
import scalatags.rx.all._

object Example {

  val c = Var("blue")
  val text = Rx(s"It is a ${c()} text!")

  def toggle(): Unit = {
    c() = if (c.now == "blue") "green" else "blue"
  }

  def main(args: Array[String]): Unit = {
    document.body.appendChild(div(color := c, onclick := toggle _, text).render)
  }

}
