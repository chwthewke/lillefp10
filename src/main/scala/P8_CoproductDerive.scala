import P2_DiffAbridged._
import shapeless._

object P8_CoproductDerive {

  implicit def cnilDiff: Diff[CNil] = new Diff[CNil] {
    override def apply( left: CNil, right: CNil ): Option[Difference] = left.impossible
  }

  implicit def cconsDiff[H, T <: Coproduct]( implicit diffHead: Diff[H], diffTail: Diff[T] ): Diff[H :+: T] =
    new Diff[H :+: T] {
      override def apply( left: H :+: T, right: H :+: T ): Option[Difference] = ( left, right ) match {
        case ( Inl( h ), Inl( i ) ) => diffHead( h, i )
        case ( Inr( t ), Inr( s ) ) => diffTail( t, s )
        case _                      => Some( differentTypes( left, right ) )
      }

      def differentTypes( left: H :+: T, right: H :+: T ): Difference = ???
    }

  implicit def sealedTraitDiff[T, C <: Coproduct](
      implicit g: Generic.Aux[T, C],
      diff: Diff[C]
  ): Diff[T] =
    new Diff[T] {
      override def apply( left: T, right: T ): Option[Difference] =
        diff.apply( g.to( left ), g.to( right ) )
    }

}
