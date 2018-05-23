import sbt._
import sbt.Keys._

object Dependencies {
  type M = ModuleID
  type D = Seq[ModuleID]

  def group( organization: String, version: String )( artifacts: String* )( testArtifacts: String* ): D =
    artifacts.map( organization       %% _ % version ) ++
      testArtifacts.map( organization %% _ % version % "test" )

  def group( artifacts: ModuleID* )( testArtifacts: ModuleID* ): D =
    artifacts ++ testArtifacts.map( _ % "test" )

  val kindProjector: D = Seq( compilerPlugin( "org.spire-math" %% "kind-projector" % "0.9.6" ) )

  val catsVersion = "1.1.0"

  val cats: D = group( "org.typelevel" %% "cats-core" % catsVersion, "org.typelevel" %% "mouse" % "0.17" )()

  val monocleVersion = "1.5.1-cats"

  val monocle: D =
    group( "com.github.julien-truffaut", monocleVersion )( "monocle-core", "monocle-macro" )()

  val shapeless: D = group( "com.chuusai" %% "shapeless" % "2.3.3" )()

  val scalatestM: M = "org.scalatest" %% "scalatest" % "3.0.5"

  val scalatest: D = group()( scalatestM )

  val scalacheckM: M = "org.scalacheck" %% "scalacheck" % "1.13.5"

  val scalacheck: D =
    group()( scalacheckM, "io.github.amrhassan" %% "scalacheck-cats" % "0.4.0" )
      .map( _.exclude( "org.typelevel", "cats-core_2.12" ) )

  val enumeratumVersion: String = "1.5.13"
  val enumeratum: D =
    group( "com.beachape" %% "enumeratum" % enumeratumVersion )()

  val common: D = kindProjector ++ cats ++ monocle ++ enumeratum ++ scalacheck ++ scalatest

  val overrides = dependencyOverrides ++= Seq(
    "org.scala-lang" % "scala-library" % scalaVersion.value,
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "org.typelevel"  %% "cats-core"    % catsVersion,
    scalacheckM
  )

  val settings = Seq( libraryDependencies ++= common, overrides )
}
