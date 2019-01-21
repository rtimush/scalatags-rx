package scalatags.rx

import java.util.concurrent.atomic.AtomicReference

import org.scalajs.dom
import org.scalajs.dom.Element
import org.scalajs.dom.ext._
import org.scalajs.dom.raw.{Comment, Node}
import rx._

import scala.collection.immutable
import scala.collection.immutable.Iterable
import scala.language.implicitConversions
import scalatags.JsDom.all._

trait RxNodeInstances {

  implicit class rxStringFrag(v: Rx[String])(implicit ctx: Ctx.Owner) extends Frag {
    def render: dom.Text = {
      val node = dom.document.createTextNode(v.now)
      v foreach { s => node.replaceData(0, node.length, s)}
      node
    }
    def applyTo(t: Element) = {
      t.appendChild(render)
    }
  }

  implicit class bindRxElement[T <: dom.Element](rx: Rx[T])(implicit ctx: Ctx.Owner) extends Modifier {
    def applyTo(container: Element) = {
      val atomicReference = new AtomicReference(rx.now)
      container.appendChild(atomicReference.get())
      rx.triggerLater {
        val current = rx.now
        val previous = atomicReference.getAndSet(current)
        container.replaceChild(current, previous): Unit
      }
    }
  }

  implicit class bindRxElements(e: Rx[immutable.Iterable[Element]])(implicit ctx: Ctx.Owner) extends Modifier {
    def applyTo(t: Element) = {
      val nonEmpty: Rx[Iterable[Node]] = e.map { t => if (t.isEmpty) List(new Comment) else t }
      val fragments = new AtomicReference(nonEmpty.now)
      nonEmpty.now foreach t.appendChild
      nonEmpty.triggerLater {
        val current: Iterable[Node] = nonEmpty.now
        val previous = fragments getAndSet current
        val i = t.childNodes.indexOf(previous.head)
        if (i < 0) throw new IllegalStateException("Children changed")
        0 until previous.size foreach (_ => t.removeChild(t.childNodes.item(i)))
        if (t.childNodes.length > i) {
          val next = t.childNodes.item(i)
          current foreach (t.insertBefore(_, next))
        } else {
          current foreach t.appendChild
        }
      }
    }
  }

}
