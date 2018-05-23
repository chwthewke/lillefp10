import shapeless.Generic

object P7_CoproductGen {

  sealed trait Color
  final case class ByName( name: String )                         extends Color
  final case class CMYK( c: Float, m: Float, y: Float, k: Float ) extends Color
  final case class RGBA( r: Float, g: Float, b: Float, a: Float ) extends Color

  val g = Generic[Color]
  // g: Generic[Color]{type Repr = ByName :+: CMYK :+: RGBA :+: CNil} = anon$macro$2$1@1f8db9e3
  // La représentation de `Color` est :
  //   ByName :+: CMYK :+: RGBA :+: CNil
}
