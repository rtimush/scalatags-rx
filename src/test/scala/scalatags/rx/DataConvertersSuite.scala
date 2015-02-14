package scalatags.rx

import rx._
import utest._
import utest.framework.TestSuite

import scalatags.DataConverters._
import scalatags.rx.DataConverters._

object DataConvertersSuite extends TestSuite {
  val tests = TestSuite {
    "for Rx[Int]" - {
      val a: Rx[Int] = Var[Int](1)
      "it should add px" - {
        assert(a.px() == 1.px)
      }
    }
    "for Var[Int]" - {
      val a: Var[Int] = Var[Int](1)
      "it should add px" - {
        assert(a.px() == 1.px)
      }
    }
  }
}
