lazy val commonSettings = Seq(
  scalaVersion := "2.12.2",
  assemblyMergeStrategy in assembly := {
    case PathList("javax", "servlet", xs@_*) => MergeStrategy.first
    case PathList(ps@_*) if ps.last endsWith ".properties" => MergeStrategy.first
    case PathList(ps@_*) if ps.last endsWith ".xml" => MergeStrategy.first
    case PathList(ps@_*) if ps.last endsWith ".types" => MergeStrategy.first
    case PathList(ps@_*) if ps.last endsWith ".class" => MergeStrategy.first
    case "application.conf" => MergeStrategy.concat
    case "unwanted.txt" => MergeStrategy.discard
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  }
)

lazy val root = (project in file("src"))
    .settings(commonSettings: _*)
    .settings(
      name := "sw-notify",
      version := "0.1.0-SNAPSHOT",
      libraryDependencies ++= Seq(
        "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
        "com.amazonaws" % "aws-lambda-java-events" % "1.3.0",
        "net.ruippeixotog" %% "scala-scraper" % "2.0.0-RC2",
        "com.github.gilbertw1" %% "slack-scala-client" % "0.2.1",
        "org.scalaj" %% "scalaj-http" % "2.3.0",
        "org.scalatest" %% "scalatest" % "3.0.1" % Test
      ),
      assemblyOutputPath in assembly := file("target/apex.jar")
    )
