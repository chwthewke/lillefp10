import shapeless._
import P2_DiffAbridged._

object P5_HListGen {

  object WhatIsGeneric {
    trait Generic[A] {
      type Repr

      def to( a: A ): Repr
      def from( r: Repr ): A
    }
  }

  trait Color
  case class Person( name: String, age: Int, favourite: Option[Color] )

  val g = shapeless.Generic[Person] // ~= implicitly[shapeless.Generic[Person]]
  // g: shapeless.Generic[Person]{type Repr = String :: Int :: Option[Color] :: shapeless.HNil} = anon$macro$8$1@635b6454
  // La représentation de `Person` est
  //     String :: Int :: Option[Color] :: HNil

  //  scala> g.to( Person("Thomas", 37, None) )
  //  res0: g.Repr = Thomas :: 37 :: None :: HNil
  //
  //  scala> g.from(res0)
  //  res1: Person = Person(Thomas,37,None)

  object derive0 {
    // implicit def diffGeneric[A]( implicit g: Generic[A], d: Diff[g.Repr] ): Diff[A] =
    //   new Diff[A] {
    //     override def apply( left: A, right: A ): Option[Difference] =
    //       d.apply( g.to( left ), g.to( right ) )
    //   }
    // illegal dependent method type: parameter may only be referenced in a subsequent parameter section
  }

  object derive1 {
    implicit def diffGeneric[A, R <: HList]( implicit g: Generic[A] { type Repr = R }, d: Diff[R] ): Diff[A] =
      new Diff[A] {
        override def apply( left: A, right: A ): Option[Difference] =
          d.apply( g.to( left ), g.to( right ) )
      }
  }

  object derive2 {
    implicit def diffGeneric[A, R <: HList]( implicit g: Generic.Aux[A, R], d: Diff[R] ): Diff[A] =
      new Diff[A] {
        override def apply( left: A, right: A ): Option[Difference] =
          d.apply( g.to( left ), g.to( right ) )
      }
  }
}
