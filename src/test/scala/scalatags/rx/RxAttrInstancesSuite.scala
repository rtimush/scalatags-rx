package scalatags.rx

import rx._
import utest._

import scalatags.JsDom.all._
import scalatags.rx.TestUtils._
import scalatags.rx.all._

object RxAttrInstancesSuite extends TestSuite {

  val tests = TestSuite {
    "string attribute" - {
      val c = Var("10px")
      "Var" - {
        val node = div(widthA := c).render
        testRx(c, node.getAttribute("width"), "10px", "20px")
      }
      "Rx" - {
        val node = div(widthA := c.rx).render
        testRx(c, node.getAttribute("width"), "10px", "20px")
      }
    }
    "int attribute" - {
      val c = Var(10)
      "Var" - {
        val node = div(widthA := c).render
        testRx(c, node.getAttribute("width"), "10", 20 -> "20")
      }
      "Rx" - {
        val node = div(widthA := c.rx).render
        testRx(c, node.getAttribute("width"), "10", 20 -> "20")
      }
      "Rx.Dynamic" - {
        val node = div(widthA := Rx(c())).render
        testRx(c, node.getAttribute("width"), "10", 20 -> "20")
      }
    }
  }

}
