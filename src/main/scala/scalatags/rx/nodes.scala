package scalatags.rx

import java.util.concurrent.atomic.AtomicReference

import org.scalajs.dom
import org.scalajs.dom.Element
import org.scalajs.dom.ext._
import org.scalajs.dom.raw.Comment
import rx._
import rx.ops._

import scala.collection.immutable
import scala.language.implicitConversions
import scalatags.JsDom.all._
import scalatags.jsdom
import scalatags.rx.ext._

trait RxNodeInstances {

  implicit def rxStringFrag(v: Rx[String]): RxStringFrag[Rx[String]] = new RxStringFrag[Rx[String]](v)
  implicit def varStringFrag(v: Var[String]): RxStringFrag[Var[String]] = new RxStringFrag[Var[String]](v)

  class RxStringFrag[F <: Rx[String]](v: F) extends jsdom.Frag {
    def render: dom.Text = {
      val node = dom.document.createTextNode(v())
      v foreach { s => node.replaceData(0, node.length, s)} attachTo node
      node
    }
  }

  implicit class bindElement[T <: dom.Element](e: Rx[T]) extends Modifier {
    def applyTo(t: Element) = {
      val element = new AtomicReference(e())
      t.appendChild(element.get())
      e foreach( { current =>
        val previous = element getAndSet current
        t.replaceChild(current, previous)
      }, skipInitial = true) attachTo t
    }
  }

  implicit class bindElements(e: Rx[immutable.Iterable[Element]]) extends Modifier {
    def applyTo(t: Element) = {
      val nonEmpty = e.map { t => if (t.isEmpty) List(new Comment) else t}
      val fragments = new AtomicReference(nonEmpty())
      nonEmpty() foreach t.appendChild
      nonEmpty foreach( { current =>
        val previous = fragments getAndSet current
        val i = t.childNodes.indexOf(previous.head)
        if (i < 0) throw new IllegalStateException("Children changed")
        0 to (previous.size - 1) foreach (_ => t.removeChild(t.childNodes.item(i)))
        if (t.childNodes.length > i) {
          val next = t.childNodes.item(i)
          current foreach (t.insertBefore(_, next))
        } else {
          current foreach t.appendChild
        }
      }, skipInitial = true)
    }
  }

}
