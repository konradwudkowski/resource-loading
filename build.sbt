import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.11.12",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "resource-loading",
    testOptions in Test += Tests.Argument("-oDF"),
    libraryDependencies += scalaTest % Test
  )
