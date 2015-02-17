package scalatags.rx

import rx._
import utest._

object TestUtils {

  implicit class RichVar[T](x: Var[T]) {
    def rx: Rx[T] = x
  }

  def testRx[T](v: Var[T], f: => T, a: T, b: T): Unit = testRx(v, f, a, b -> b)

  def testRx[S, T](v: Var[S], f: => T, a: T, b: (S, T)): Unit = {
    assert(f == a)
    v() = b._1
    assert(f == b._2)
  }


}
