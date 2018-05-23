import cats.data.NonEmptyList

object P2_DiffAbridged {

  trait Diff[A] {
    def apply( left: A, right: A ): Option[Difference]
  }

  sealed trait Difference

  object Difference {

    final case class Value( left: String, right: String )                 extends Difference
    final case class Coproduct( name: String, difference: Difference )    extends Difference
    final case class Product( name: String, fields: NonEmptyList[Field] ) extends Difference
    final case class Tuple( name: String, fields: NonEmptyList[Index] )   extends Difference
    final case class Seq( name: String, diffs: NonEmptyList[Index] )      extends Difference

    final case class Field( name: String, difference: Difference )
    final case class Index( index: Int, difference: Difference )
  }

}
