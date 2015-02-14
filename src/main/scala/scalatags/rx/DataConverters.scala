package scalatags.rx

import rx._
import rx.ops._

import scala.language.implicitConversions
import scalatags.DataConverters._

object DataConverters extends DataConverters

trait DataConverters {

  implicit def RxInt2RxCssNumber(x: Rx[Int]): RxCssNumber[Int] = new RxCssNumber[Int](x)
  implicit def RxDouble2CssNumber(x: Rx[Double]): RxCssNumber[Double] = new RxCssNumber[Double](x)
  implicit def RxFloat2CssNumber(x: Rx[Float]): RxCssNumber[Float] = new RxCssNumber[Float](x)
  implicit def RxLong2CssNumber(x: Rx[Long]): RxCssNumber[Long] = new RxCssNumber[Long](x)
  implicit def RxShort2CssNumber(x: Rx[Short]): RxCssNumber[Short] = new RxCssNumber[Short](x)
  implicit def RxByte2CssNumber(x: Rx[Byte]): RxCssNumber[Byte] = new RxCssNumber[Byte](x)

  class RxCssNumber[T](x: Rx[T])(implicit cssNumber: T => CssNumber[T]) {
    def px = x map (_.px)
    def pt = x map (_.pt)
    def mm = x map (_.mm)
    def cm = x map (_.cm)
    def in = x map (_.in)
    def pc = x map (_.pc)
    def em = x map (_.em)
    def ch = x map (_.ch)
    def ex = x map (_.ex)
    def rem = x map (_.rem)
    def deg = x map (_.deg)
    def grad = x map (_.grad)
    def rad = x map (_.rad)
    def turn = x map (_.turn)
    def pct = x map (_.pct)
  }

}
