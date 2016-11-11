package scalatags.rx

import rx._

import scala.language.implicitConversions

trait RxDataConverters {

  implicit def RxInt2RxCssNumber(x: Rx[Int]): RxCssNumber[Int] = new RxCssNumber[Int](x)
  implicit def RxDouble2CssNumber(x: Rx[Double]): RxCssNumber[Double] = new RxCssNumber[Double](x)
  implicit def RxFloat2CssNumber(x: Rx[Float]): RxCssNumber[Float] = new RxCssNumber[Float](x)
  implicit def RxLong2CssNumber(x: Rx[Long]): RxCssNumber[Long] = new RxCssNumber[Long](x)
  implicit def RxShort2CssNumber(x: Rx[Short]): RxCssNumber[Short] = new RxCssNumber[Short](x)
  implicit def RxByte2CssNumber(x: Rx[Byte]): RxCssNumber[Byte] = new RxCssNumber[Byte](x)

}

class RxCssNumber[T](val x: Rx[T]) extends AnyVal {
  def px(implicit ctx: Ctx.Owner) = x map (_ + "px")
  def pt(implicit ctx: Ctx.Owner) = x map (_ + "pt")
  def mm(implicit ctx: Ctx.Owner) = x map (_ + "mm")
  def cm(implicit ctx: Ctx.Owner) = x map (_ + "cm")
  def in(implicit ctx: Ctx.Owner) = x map (_ + "in")
  def pc(implicit ctx: Ctx.Owner) = x map (_ + "pc")
  def em(implicit ctx: Ctx.Owner) = x map (_ + "em")
  def ch(implicit ctx: Ctx.Owner) = x map (_ + "ch")
  def ex(implicit ctx: Ctx.Owner) = x map (_ + "ex")
  def rem(implicit ctx: Ctx.Owner) = x map (_ + "rem")
  def deg(implicit ctx: Ctx.Owner) = x map (_ + "deg")
  def grad(implicit ctx: Ctx.Owner) = x map (_ + "grad")
  def rad(implicit ctx: Ctx.Owner) = x map (_ + "rad")
  def turn(implicit ctx: Ctx.Owner) = x map (_ + "turn")
  def pct(implicit ctx: Ctx.Owner) = x map (_ + "pct")
}

