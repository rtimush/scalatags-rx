package scalatags

package object rx {

  object ext extends RxExt

  object all extends RxStyleInstances with RxAttrInstances with RxNodeInstances with RxDataConverters

}
