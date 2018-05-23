import sbt._
import sbt.Keys._
import sbtbuildinfo._
import sbtbuildinfo.BuildInfoKeys._
import sbtrelease.Version

object SbtBuildInfo {
  val shortVersion = SettingKey[String]( "short-version" )

  def buildSettings( objectName: String, targetPackage: String = "fr.thomasdufour.lillefp10" ) =
    BuildInfoPlugin.projectSettings ++ Seq(
      buildInfoPackage := targetPackage,
      buildInfoObject := objectName,
      shortVersion := shortenVersion( version.value ),
      buildInfoKeys := BuildInfoKey.ofN( name, version, shortVersion, scalaVersion )
    )

  val MajorMinor = raw"(\d+)\.(\d+).*".r

  private def shortenVersion( version: String ): String =
    version match {
      case MajorMinor( maj, min ) => s"$maj.$min"
      case _                      => throw new IllegalArgumentException( s"Could not parse version $version" )
    }

}
