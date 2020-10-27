name := "scala-ims"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "org.reactivemongo" %% "reactivemongo" % "1.0.0",
  "org.slf4j" % "slf4j-log4j12" % "1.7.30",
  "org.scala-lang.modules" %% "scala-swing" % "2.1.1"
)

//// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
//libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.30"
//
//// https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
//libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.30"
