import P2_DiffAbridged._
import shapeless._

object P9_Lazy {

  sealed trait MyList[+A]
  case object MyNil                                 extends MyList[Nothing]
  case class MyCons[+A]( head: A, tail: MyList[A] ) extends MyList[A]

  implicit val diffInt: Diff[Int] = ???

  implicit val diffHNil: Diff[HNil]                                                           = ???
  implicit def diffHCons[H, T <: HList]( implicit H: Diff[H], T: Diff[T] ): Diff[H :: T]      = ???
  implicit val diffCNil: Diff[CNil]                                                           = ???
  implicit def diffCCons[H, T <: Coproduct]( implicit H: Diff[H], T: Diff[T] ): Diff[H :+: T] = ???
  implicit def diffGen[A, R]( implicit G: Generic.Aux[A, R], D: Diff[R] ): Diff[A]            = ???

  // implicitly[Diff[MyList[Int]]]
  // [error] diverging implicit expansion for type P2_DiffAbridged.Diff[P9_Lazy.MyList[Int]]
  // [error] starting with method diffGen

}
