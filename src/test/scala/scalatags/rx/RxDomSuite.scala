package scalatags.rx

import rx._
import utest._
import utest.framework.TestSuite

import scalatags.JsDom.all._
import scalatags.rx.RxDom._

object RxDomSuite extends TestSuite {
  val tests = TestSuite {
    "Var[String] style" - {
      val c = Var("blue")
      val node = div(color := c).render
      "should set initial value" - {
        assert(node.style.getPropertyValue("color") == "blue")
      }
      "should propagate updates" - {
        c() = "green"
        assert(node.style.getPropertyValue("color") == "green")
      }
    }
    "Rx[String] style" - {
      val c = Var("blue")
      val node = div(color := (c: Rx[String])).render
      "should set initial value" - {
        assert(node.style.getPropertyValue("color") == "blue")
      }
      "should propagate updates" - {
        c() = "green"
        assert(node.style.getPropertyValue("color") == "green")
      }
    }
  }
}
