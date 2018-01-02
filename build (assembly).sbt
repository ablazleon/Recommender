organization := "com.micronautics"

name := "FBID-Recomendador" // TODO provide a short yet descriptive name

version := "0.1.0"

scalaVersion := "2.11.8"

val sparkVersion = "2.2.0"

// If you want to apply a license, such as the Apache 2 license, uncomment the following:
licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

// The REPL can’t cope with -Ywarn-unused:imports or -Xfatal-warnings so turn them off for the console
scalacOptions in (Compile, console) --= Seq("-Ywarn-unused:imports", "-Xfatal-warnings")

scalacOptions in (Compile, doc) ++= baseDirectory.map {
  (bd: File) => Seq[String](
    "-sourcepath", bd.getAbsolutePath, // todo replace my-new-project with the github project name, and replace mslinn with your github id
    "-doc-source-url", "https://github.com/mslinn/my-new-project/tree/master€{FILE_PATH}.scala"
  )
}.value

javacOptions ++= Seq(
  "-source", "1.8",
  "-target", "1.8",
  "-g:vars"
)

resolvers ++= Seq(
)

libraryDependencies ++= Seq(

  "org.scalatest"     %% "scalatest"   % "3.0.3" % Test withSources(),
  "junit"             %  "junit"       % "4.12"  % Test,
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion
)

logLevel := Level.Warn

// Only show warnings and errors on the screen for compilations.
// This applies to both test:compile and compile and is Info by default
logLevel in compile := Level.Warn

// Level.INFO is needed to see detailed output when running tests
logLevel in test := Level.Info

// define the statements initially evaluated when entering 'console', 'console-quick', but not 'console-project'
initialCommands in console := """
                                |""".stripMargin

cancelable := true

fork in run := true

outputStrategy := Some(StdoutOutput)

//Fuente: http://queirozf.com/entries/creating-scala-fat-jars-for-spark-on-sbt-with-sbt-assembly-plugin
assemblyMergeStrategy in assembly := {
  case PathList("org","aopalliance", xs @ _*) => MergeStrategy.last
  case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("com", "google", xs @ _*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
  case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
  case "META-INF/mailcap" => MergeStrategy.last
  case "META-INF/mimetypes.default" => MergeStrategy.last
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case "overview.html" => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}