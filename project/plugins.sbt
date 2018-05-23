name := "lillefp10-build"

resolvers += Classpaths.sbtPluginReleases
resolvers += Resolver.sonatypeRepo( "releases" )
resolvers += Resolver.sonatypeRepo( "snapshots" )

addSbtPlugin( "org.scoverage"     % "sbt-scoverage"       % "1.5.1" )
addSbtPlugin( "com.eed3si9n"      % "sbt-buildinfo"       % "0.8.0" )
addSbtPlugin( "com.typesafe.sbt"  % "sbt-native-packager" % "1.3.3" )
addSbtPlugin( "com.github.gseitz" % "sbt-release"         % "1.0.7" )
addSbtPlugin( "com.geirsson"      % "sbt-scalafmt"        % "1.4.0" )
