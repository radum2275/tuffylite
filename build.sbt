import org.allenai.plugins.CoreRepositories.Licenses

lazy val buildSettings = Seq(
  organization := "edu.stanford.hazy",
  description := "An internal version of Tuffy downloaded from: http://i.stanford.edu/hazy/tuffy/download/",
  publishMavenStyle := true,
  publishArtifact in (Compile, packageDoc) := false,   // to avoid "javadoc: error - invalid flag: -target"
  licenses += Licenses.apache2,
  homepage := Some(url("https://github.com/allenai/tuffylite")),
  scmInfo := Some(ScmInfo(
    url("https://github.com/allenai/tuffylite"),
    "https://github.com/allenai/tuffylite.git")),
  pomExtra := (
    <developers>
      <developer>
        <id>allenai-dev-role</id>
        <name>Allen Institute for Artificial Intelligence</name>
        <email>dev-role@allenai.org</email>
      </developer>
    </developers>),
  // TODO (colinarenz): When this is included in sbt-plugins, it can be removed.
  bintrayOrganization := Some("allenai")
)

lazy val tuffy = Project(id = "tuffy-internal", base = file("."))
  .settings(buildSettings)
  .settings(PublishTo.ai2BintrayPublic)

resolvers ++= Seq(
  "scala-tools.org" at "http://scala-tools.org/repo-releases",
  "conjars" at "http://conjars.org/repo",
  "apache.releases" at "https://repository.apache.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "args4j" % "args4j" % "2.0.16",
  "junit" % "junit" % "4.11",
  "org.antlr" % "antlr" % "3.2",
  "org.apache.commons" % "commons-lang3" % "3.3",
  "org.apache.commons" % "commons-math" % "2.2",
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc41",
  "org.jgrapht" % "jgrapht-core" % "1.1.0"
)

compileOrder := CompileOrder.JavaThenScala

javaOptions += "-Xmx4G"
scalacOptions ++= Seq("-Xlint", "-deprecation", "-feature")
