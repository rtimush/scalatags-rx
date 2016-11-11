package scalatags.rx

import org.scalajs.dom.Element
import rx._

import scalatags.JsDom.all._
import scalatags.rx.ext._

trait RxAttrInstances {

  implicit def rxAttrValue[T: AttrValue](implicit ctx: Ctx.Owner): AttrValue[Rx[T]] = new RxAttrValue[T, Rx[T]]
  implicit def varAttrValue[T: AttrValue](implicit ctx: Ctx.Owner): AttrValue[Var[T]] = new RxAttrValue[T, Var[T]]

  class RxAttrValue[T, F <: Rx[T]](implicit av: AttrValue[T], ctx: Ctx.Owner) extends AttrValue[F] {
    override def apply(t: Element, a: Attr, rv: F): Unit = {
      rv foreach { v => av.apply(t, a, v)} attachTo t
    }
  }

}

