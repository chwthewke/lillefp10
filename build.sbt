import sbt._
import sbt.Keys._

// format: off
scalaOrganization in ThisBuild := "org.scala-lang"
scalaVersion      in ThisBuild := "2.12.4"
conflictManager   in ThisBuild := ConflictManager.strict
// format: on

enablePlugins( FormatPlugin, ScalacPlugin )

val sharedSettings = Seq( organization := "fr.thomasdufour" )

val lillefp10Settings =
  Defaults.coreDefaultSettings ++
    sharedSettings ++
    Dependencies.settings :+
    (testOptions in Test += Tests.Argument( TestFrameworks.ScalaTest, "-oDF" ))

val `lillefp10-core` = project
  .settings( lillefp10Settings )
  .settings( SbtBuildInfo.buildSettings( "Lillefp10CoreBuildInfo" ) )
  .settings( Console.coreImports.settings )

val `lillefp10` = project
  .in( file( "." ) )
  .settings( sharedSettings )
  .settings( Dependencies.overrides )
  .aggregate( `lillefp10-core` )
