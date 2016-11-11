package scalatags.rx

import rx._
import utest._

import scalatags.DataConverters._
import scalatags.rx.TestUtils._
import scalatags.rx.all._

object RxDataConvertersSuite extends TestSuite {
  val tests = TestSuite {
    "it should add px for integer" - {
      val a = Var[Int](1)
      "Var" - {
        assert(a.px.now == 1.px)
      }
      "Rx" - {
        assert(a.rx.px.now == 1.px)
      }
    }
  }
}
