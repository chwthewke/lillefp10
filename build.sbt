import sbt._
import sbt.Keys._

addCompilerPlugin("io.tryp" % "splain" % "0.3.1" cross CrossVersion.patch)

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

val `lillefp10` = project
  .in( file( "." ) )
  .settings( lillefp10Settings )
  .settings( SbtBuildInfo.buildSettings( "Lillefp10CoreBuildInfo" ) )
  .settings( Console.coreImports.settings )
  .settings( libraryDependencies ++= Seq( "auto-diff-core", "auto-diff-enumeratum", "auto-diff-generic" ).map(
    "fr.thomasdufour" %% _ % "0.2.1.0-SNAPSHOT" ) )
