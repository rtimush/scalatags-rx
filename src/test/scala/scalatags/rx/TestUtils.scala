package scalatags.rx

import rx._
import utest._

object TestUtils {

  implicit class RichVar[T](x: Var[T]) {
    def rx: Rx[T] = x
  }

  def testRx[T](v: Var[T], f: => T, a: T, b: T): Unit = testRx(v, f, a, b -> b)

  def testRx[S, T](v: Var[S], fn: => T, initial: T, newValue: (S, T)): Unit = {
    assert(fn == initial)
    v() = newValue._1
    
    // scalarx is asynchronous in nature. Check a few times if given a false negative.
    def hasChanged(rt: Int): Boolean = {
      fn == newValue._2 || rt > 0 && hasChanged(rt - 1)
    }

    hasChanged(3)
  }

}
