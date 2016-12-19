package scalatags.rx

import org.scalajs.dom._
import rx._

import scala.language.implicitConversions
import scalatags.JsDom.all._
import scalatags.generic.{Style, StylePair}

trait RxStyleInstances {

  implicit def varIsRxForStyleValue[T](implicit x: StyleValue[Rx[T]], ctx: Ctx.Owner): StyleValue[Var[T]] =
    new StyleValue[Var[T]] {
      override def apply(t: Element, s: Style, v: Var[T]): Unit = x.apply(t, s, v)
    }
  implicit def varIsRxForPixelStyleValue[T](implicit x: PixelStyleValue[Rx[T]], ctx: Ctx.Owner): PixelStyleValue[Var[T]] =
    new PixelStyleValue[Var[T]] {
      override def apply(s: Style, v: Var[T]): StylePair[Element, _] = x.apply(s, v)
    }

  implicit def rxDynIsRxForStyleValue[T](implicit x: StyleValue[Rx[T]], ctx: Ctx.Owner): StyleValue[Rx.Dynamic[T]] =
    new StyleValue[Rx.Dynamic[T]] {
      override def apply(t: Element, s: Style, v: Rx.Dynamic[T]): Unit = x.apply(t, s, v)
    }
  implicit def rxDynIsRxForPixelStyleValue[T](implicit x: PixelStyleValue[Rx[T]], ctx: Ctx.Owner): PixelStyleValue[Rx.Dynamic[T]] =
    new PixelStyleValue[Rx.Dynamic[T]] {
      override def apply(s: Style, v: Rx.Dynamic[T]): StylePair[Element, _] = x.apply(s, v)
    }

  implicit def rxStyleValue[T: StyleValue](implicit ctx: Ctx.Owner): StyleValue[Rx[T]] = new RxStyleValue[T]

  def rxPixelStyleValue[T: StyleValue](implicit ctx: Ctx.Owner): PixelStyleValue[Rx[T]] = genericPixelStyle[Rx[T]]
  def rxPixelStyleValuePx[T](implicit ev: StyleValue[Rx[String]], ctx: Ctx.Owner): PixelStyleValue[Rx[T]] = new RxGenericPixelStylePx[T](ev)

  implicit def rxStringPixelStyle(implicit ctx: Ctx.Owner) = rxPixelStyleValue[String]
  implicit def rxBooleanPixelStyle(implicit ctx: Ctx.Owner) = rxPixelStyleValue[Boolean]
  implicit def rxBytePixelStyle(implicit ctx: Ctx.Owner) = rxPixelStyleValuePx[Byte]
  implicit def rxShortPixelStyle(implicit ctx: Ctx.Owner) = rxPixelStyleValuePx[Short]
  implicit def rxIntPixelStyle(implicit ctx: Ctx.Owner) = rxPixelStyleValuePx[Int]
  implicit def rxLongPixelStyle(implicit ctx: Ctx.Owner) = rxPixelStyleValuePx[Long]
  implicit def rxFloatPixelStyle(implicit ctx: Ctx.Owner) = rxPixelStyleValuePx[Float]
  implicit def rxDoublePixelStyle(implicit ctx: Ctx.Owner) = rxPixelStyleValuePx[Double]

  class RxStyleValue[T](implicit sv: StyleValue[T], ctx: Ctx.Owner) extends StyleValue[Rx[T]] {
    override def apply(t: Element, s: Style, rv: Rx[T]): Unit = {
      rv foreach { v => sv.apply(t, s, v)}
    }
  }

  class RxGenericPixelStylePx[T](ev: StyleValue[Rx[String]])(implicit ctx: Ctx.Owner) extends PixelStyleValue[Rx[T]] {
    def apply(s: Style, v: Rx[T]) = StylePair(s, v.map(_ + "px"), ev)
  }

}
