object P1_DiffExample {

  case class Address( street: String, city: String )
  case class Person( name: String, age: Int, address: Address )

  import fr.thomasdufour.autodiff._
  import Difference._
  import generic.auto._

  implicitly[Diff[Person]]
    .apply( Person( "Jean Martin", 29, Address( "2 rue Pasteur", "Lille" ) ),
           Person( "Jean Martin", 55, Address( "2 rue Pasteur", "Lyon" ) ) )
    .foreach( r => println( Pretty.Colorized2.show( r ) ) )

  def main( args: Array[String] ): Unit = {}
}
