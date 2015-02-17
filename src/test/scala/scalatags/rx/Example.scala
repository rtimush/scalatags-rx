package scalatags.rx

import org.scalajs.dom.document
import rx._

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scalatags.JsDom.all._
import scalatags.rx.all._

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
