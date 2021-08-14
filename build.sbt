name := "codility"

version := "0.1"

scalaVersion := "2.13.6"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")


// Testing
val spec2Version = "4.10.4"
libraryDependencies ++= Seq(
  "org.specs2"                             %% "specs2-core"                 % spec2Version % Test,
  "org.specs2"                             %% "specs2-mock"                 % spec2Version % Test,
  "org.specs2"                             %% "specs2-junit"                % spec2Version % Test
)
