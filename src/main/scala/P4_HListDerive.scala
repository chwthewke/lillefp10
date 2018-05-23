import shapeless._
import P2_DiffAbridged._

object P4_HListDerive {

  implicit val hnilDiff: Diff[HNil] =
    new Diff[HNil] {
      override def apply( left: HNil, right: HNil ): Option[Difference] = None
    }

  implicit def hconsDiff[H, T <: HList]( implicit diffHead: Diff[H], diffTail: Diff[T] ): Diff[H :: T] =
    new Diff[H :: T] {
      def combineDifferences( h: Option[Difference], t: Option[Difference] ): Option[Difference] = ???

      override def apply( left: H :: T, right: H :: T ): Option[Difference] = {
        val headDiff = diffHead.apply( left.head, right.head )
        val tailDiff = diffTail.apply( left.tail, right.tail )
        combineDifferences( headDiff, tailDiff )
      }
    }

  implicit def diffInt: Diff[Int]       = ???
  implicit def diffString: Diff[String] = ???

  implicitly[Diff[Int :: String :: HNil]]

}
