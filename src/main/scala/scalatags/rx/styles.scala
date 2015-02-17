package scalatags.rx

import org.scalajs.dom._
import rx._
import rx.ops._

import scala.language.implicitConversions
import scalatags.JsDom.all._
import scalatags.generic.{Style, StylePair}
import scalatags.rx.ext._

trait RxStyleInstances {

  implicit def varIsRxForStyleValue[T](implicit x: StyleValue[Rx[T]]): StyleValue[Var[T]] =
    new StyleValue[Var[T]] {
      override def apply(t: Element, s: Style, v: Var[T]): Unit = x.apply(t, s, v)
    }
  implicit def varIsRxForPixelStyleValue[T](implicit x: PixelStyleValue[Rx[T]]): PixelStyleValue[Var[T]] =
    new PixelStyleValue[Var[T]] {
      override def apply(s: Style, v: Var[T]): StylePair[Element, _] = x.apply(s, v)
    }

  implicit def rxStyleValue[T: StyleValue]: StyleValue[Rx[T]] = new RxStyleValue[T]

  def rxPixelStyleValue[T: StyleValue]: PixelStyleValue[Rx[T]] = genericPixelStyle[Rx[T]]
  def rxPixelStyleValuePx[T](implicit ev: StyleValue[Rx[String]]): PixelStyleValue[Rx[T]] = new RxGenericPixelStylePx[T](ev)

  implicit val rxStringPixelStyle = rxPixelStyleValue[String]
  implicit val rxBooleanPixelStyle = rxPixelStyleValue[Boolean]
  implicit val rxBytePixelStyle = rxPixelStyleValuePx[Byte]
  implicit val rxShortPixelStyle = rxPixelStyleValuePx[Short]
  implicit val rxIntPixelStyle = rxPixelStyleValuePx[Int]
  implicit val rxLongPixelStyle = rxPixelStyleValuePx[Long]
  implicit val rxFloatPixelStyle = rxPixelStyleValuePx[Float]
  implicit val rxDoublePixelStyle = rxPixelStyleValuePx[Double]

  class RxStyleValue[T](implicit sv: StyleValue[T]) extends StyleValue[Rx[T]] {
    override def apply(t: Element, s: Style, rv: Rx[T]): Unit = {
      rv foreach { v => sv.apply(t, s, v)} attachTo t
    }
  }

  class RxGenericPixelStylePx[T](ev: StyleValue[Rx[String]]) extends PixelStyleValue[Rx[T]] {
    def apply(s: Style, v: Rx[T]) = StylePair(s, v.map(_ + "px"), ev)
  }

}
