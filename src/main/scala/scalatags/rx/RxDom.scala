package scalatags.rx

import org.scalajs.dom.Element
import rx._
import rx.ops._

import scala.language.implicitConversions
import scala.scalajs.js
import scalatags.JsDom.all._
import scalatags.generic.Style

object RxDom {

  implicit def rxStyleValue[T: StyleValue]: StyleValue[Rx[T]] = new RxStyleValue[T, Rx[T]]
  implicit def varStyleValue[T: StyleValue]: StyleValue[Var[T]] = new RxStyleValue[T, Var[T]]

  private trait ReferenceHolder extends js.Object {
    var `scalatags.rx.refs`: js.UndefOr[js.Array[Any]] = js.native
  }

  implicit class ObsExt(val o: Obs) extends AnyVal {
    def attachTo(e: Element): Unit = {
      val holder = e.asInstanceOf[ReferenceHolder]
      if (holder.`scalatags.rx.refs`.isEmpty) {
        holder.`scalatags.rx.refs` = js.Array[Any]()
      }
      holder.`scalatags.rx.refs`.get.push(e)
    }
  }

  class RxStyleValue[T, F <: Rx[T]](implicit sv: StyleValue[T]) extends StyleValue[F] {
    override def apply(t: Element, s: Style, rv: F): Unit = {
      rv foreach { v => sv.apply(t, s, v)} attachTo t
    }
  }

}
