import shapeless._

object P3_HLists {

  type T = Int :: String :: HNil // T <: HList

  object alt {

    type T = ::[Int, ::[String, HNil]]
  }

  val t: T = 1 :: "abc" :: HNil

  // val s: T = 1 :: 2 :: "abc" :: HNil
  // [error]  found   : Int :: Int :: String :: shapeless.HNil
  // [error]  required: T
  // [error]     (which expands to)  Int :: String :: shapeless.HNil
  // [error]   val s: T = 1 :: 2 :: "abc" :: HNil

}
