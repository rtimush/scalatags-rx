package scalatags.rx

import org.scalajs.dom
import org.scalajs.dom.{Element, Node}
import rx._
import rx.ops._

import scala.language.implicitConversions
import scala.scalajs.js
import scalatags.JsDom.all._
import scalatags.generic.{Style, StylePair}
import scalatags.jsdom

object RxDom {

  implicit def rxStyleValue[T: StyleValue]: StyleValue[Rx[T]] = new RxStyleValue[T, Rx[T]]
  implicit def varStyleValue[T: StyleValue]: StyleValue[Var[T]] = new RxStyleValue[T, Var[T]]

  def rxPixelStyleValue[T: StyleValue]: PixelStyleValue[Rx[T]] = genericPixelStyle[Rx[T]]
  def varPixelStyleValue[T: StyleValue]: PixelStyleValue[Var[T]] = genericPixelStyle[Var[T]]
  def rxPixelStyleValuePx[T](implicit ev: StyleValue[Rx[String]]): PixelStyleValue[Rx[T]] = new RxGenericPixelStylePx[T, Rx[T]](ev)
  def varPixelStyleValuePx[T](implicit ev: StyleValue[Rx[String]]): PixelStyleValue[Var[T]] = new RxGenericPixelStylePx[T, Var[T]](ev)

  implicit val rxStringPixelStyle = rxPixelStyleValue[String]
  implicit val varStringPixelStyle = varPixelStyleValue[String]
  implicit val rxBooleanPixelStyle = rxPixelStyleValue[Boolean]
  implicit val varBooleanPixelStyle = varPixelStyleValue[Boolean]
  implicit val rxBytePixelStyle = rxPixelStyleValuePx[Byte]
  implicit val varBytePixelStyle = varPixelStyleValuePx[Byte]
  implicit val rxShortPixelStyle = rxPixelStyleValuePx[Short]
  implicit val varShortPixelStyle = varPixelStyleValuePx[Short]
  implicit val rxIntPixelStyle = rxPixelStyleValuePx[Int]
  implicit val varIntPixelStyle = varPixelStyleValuePx[Int]
  implicit val rxLongPixelStyle = rxPixelStyleValuePx[Long]
  implicit val varLongPixelStyle = varPixelStyleValuePx[Long]
  implicit val rxFloatPixelStyle = rxPixelStyleValuePx[Float]
  implicit val varFloatPixelStyle = varPixelStyleValuePx[Float]
  implicit val rxDoublePixelStyle = rxPixelStyleValuePx[Double]
  implicit val varDoublePixelStyle = varPixelStyleValuePx[Double]

  implicit def rxAttrValue[T: AttrValue]: AttrValue[Rx[T]] = new RxAttrValue[T, Rx[T]]
  implicit def varAttrValue[T: AttrValue]: AttrValue[Var[T]] = new RxAttrValue[T, Var[T]]

  implicit def rxStringFrag(v: Rx[String]): RxStringFrag[Rx[String]] = new RxStringFrag[Rx[String]](v)
  implicit def varStringFrag(v: Var[String]): RxStringFrag[Var[String]] = new RxStringFrag[Var[String]](v)

  private trait ReferenceHolder extends js.Object {
    var `scalatags.rx.refs`: js.UndefOr[js.Array[Any]] = js.native
  }

  implicit class ObsExt(val o: Obs) extends AnyVal {
    def attachTo(e: Node): Unit = {
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

  class RxGenericPixelStylePx[T, F <: Rx[T]](ev: StyleValue[Rx[String]]) extends PixelStyleValue[F] {
    def apply(s: Style, v: F) = StylePair(s, v.map(_ + "px"), ev)
  }

  class RxAttrValue[T, F <: Rx[T]](implicit av: AttrValue[T]) extends AttrValue[F] {
    override def apply(t: Element, a: Attr, rv: F): Unit = {
      rv foreach { v => av.apply(t, a, v)} attachTo t
    }
  }

  class RxStringFrag[F <: Rx[String]](v: F) extends jsdom.Frag {
    def render: dom.Text = {
      val node = dom.document.createTextNode(v())
      v foreach { s => node.replaceData(0, node.length, s)} attachTo node
      node
    }
  }

}
