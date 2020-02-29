organization := "com.gluegadget"
moduleName := "justified-collection"
description := "Wrapper around standard library collections that provides methods to ensure existence of keys in collections"

scalaVersion := "2.13.1"

homepage := Some(url("https://github.com/amir/justified-collection"))
licenses := Seq("CC0" -> url("https://creativecommons.org/publicdomain/zero/1.0/"))

lazy val V = new {
  val specs2 = "4.8.3"
}

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core"          % V.specs2 % Test,
  "org.specs2" %% "specs2-matcher-extra" % V.specs2 % Test,
  "org.specs2" %% "specs2-scalacheck"    % V.specs2 % Test
)

addCommandAlias("fmt", ";scalafmtAll;scalafmtSbt")
