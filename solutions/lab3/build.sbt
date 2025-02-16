scalaVersion := "2.13.10"

scalacOptions ++= Seq(
  "-feature",
  "-language:reflectiveCalls",
)

// Chisel 3.5
addCompilerPlugin("edu.berkeley.cs" % "chisel3-plugin" % "3.5.6" cross CrossVersion.full)
libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "3.5.6"
libraryDependencies += "edu.berkeley.cs" %% "chiseltest" % "0.5.6"
