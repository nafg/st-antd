addSbtPlugin("org.scala-js"                % "sbt-scalajs"                % "1.19.0")
addSbtPlugin("org.scalablytyped.converter" % "sbt-converter"              % "1.0.0-beta45")
addSbtPlugin("com.github.sbt"              % "sbt-ci-release"             % "1.11.1")
addSbtPlugin("io.github.nafg.mergify"      % "sbt-mergify-github-actions" % "0.9.0")
// sbt-converter 1.0.0-beta45 needs scala-parser-combinators 2.x; sbt-pgp (via sbt-ci-release) and ssl-config select 1.x.
libraryDependencySchemes += "org.scala-lang.modules" %% "scala-parser-combinators" % VersionScheme.Always
