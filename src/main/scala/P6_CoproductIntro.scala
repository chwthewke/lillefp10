import shapeless._

object P6_CoproductIntro {

  // Rappel HList
  type T = Int :: String :: HNil // <: HList
  val t: T = 1 :: "abc" :: HNil

  type S = Int :+: String :+: CNil // <: Coproduct
  val s1: S = Inl( 1 )
  val s2: S = Inr( Inl( "abc" ) )

  // val s3: S = Inl( "abc" )
  // <console>:30: error: type mismatch;
  //  found   : String("abc")
  //  required: Int
  //        val s3: S = Inl( "abc" )

}
