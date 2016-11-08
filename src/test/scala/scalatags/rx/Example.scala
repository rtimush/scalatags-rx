package scalatags.rx

import org.scalajs.dom.document
import rx._

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scalatags.JsDom.all._
import scalatags.rx.all._

import rx.Ctx.Owner.Unsafe._

object Example extends JSApp {

  val c = Var("blue")
  val text = Rx(s"It is a ${c()} text!")

  def toggle(): Unit = {
    c() = if (c.now == "blue") "green" else "blue"
  }

  override def main(): Unit = {
    document.body.appendChild(
      div(
        color := c, onclick := toggle _,
        text
      ).render
    )
  }

}
