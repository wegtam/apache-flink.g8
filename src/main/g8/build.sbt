// *****************************************************************************
// Projects
// *****************************************************************************

lazy val root =
  project
    .in(file("."))
    .enablePlugins(AutomateHeaderPlugin, GitVersioning, GitBranchPrompt)
    .settings(settings)
    .settings(
      name := "$name$",
      libraryDependencies ++= Seq(
        library.flinkCassandra,
        library.flinkClients,
        library.flinkElastic,
        library.flinkHDFS,
        library.flinkScala,
        library.flinkStreaming,
        library.flinkTable,
        library.nettyAll,
        library.postgresql,
        library.scalaCheck % Test,
        library.scalaTest  % Test
      ),
      fork in run := true
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val flink      = "1.4.0"
      val netty      = "4.1.21.Final"
      val postgresql = "42.1.4"
      val scalaCheck = "1.13.5"
      val scalaTest  = "3.0.4"
    }
    val flinkCassandra = "org.apache.flink"    %% "flink-connector-cassandra"      % Version.flink
    val flinkClients   = "org.apache.flink"    %% "flink-clients"                  % Version.flink
    val flinkElastic   = "org.apache.flink"    %% "flink-connector-elasticsearch5" % Version.flink
    val flinkHDFS      = "org.apache.flink"    %% "flink-connector-filesystem"     % Version.flink
    val flinkScala     = "org.apache.flink"    %% "flink-scala"                    % Version.flink
    val flinkStreaming = "org.apache.flink"    %% "flink-streaming-scala"          % Version.flink
    val flinkTable     = "org.apache.flink"    %% "flink-table"                    % Version.flink
    val nettyAll       = "io.netty"            %  "netty-all"                      % Version.netty
    val postgresql     = "org.postgresql"      %  "postgresql"                     % Version.postgresql
    val scalaCheck     = "org.scalacheck"      %% "scalacheck"                     % Version.scalaCheck
    val scalaTest      = "org.scalatest"       %% "scalatest"                      % Version.scalaTest
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
    gitSettings ++
    scalafmtSettings

lazy val commonSettings =
  Seq(
    scalaVersion := "$scala_version$",
    organization := "$organisation$",
    organizationName := "$organisation_name$",
    startYear := Some(2018),
    licenses += ("AGPL-3.0", url("https://www.gnu.org/licenses/agpl.html")),
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-explaintypes",
      "-feature",
      "-language:_",
      "-target:jvm-1.8",
      "-unchecked",
      "-Xfatal-warnings",
      "-Xfuture",
      "-Xlint:adapted-args",
      "-Xlint:by-name-right-associative",
      "-Xlint:delayedinit-select",
      "-Xlint:doc-detached",
      "-Xlint:inaccessible",
      "-Xlint:infer-any",
      "-Xlint:missing-interpolator",
      "-Xlint:nullary-override",
      "-Xlint:nullary-unit",
      "-Xlint:option-implicit",
      "-Xlint:package-object-classes",
      "-Xlint:poly-implicit-overload",
      "-Xlint:private-shadow",
      "-Xlint:stars-align",
      "-Xlint:type-parameter-shadow",
      "-Xlint:unsound-match",
      "-Ypartial-unification",
      "-Ywarn-numeric-widen",
      "-Ywarn-unused-import",
      "-Ywarn-value-discard"
    ),
    scalacOptions in (Compile, console) --= Seq("-Ywarn-unused-import", "-Xfatal-warnings"),
    (unmanagedSourceDirectories in Compile) := Seq((scalaSource in Compile).value),
    (unmanagedSourceDirectories in Test) := Seq((scalaSource in Test).value)
  )

lazy val gitSettings =
  Seq(
    git.useGitDescribe := true
  )

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true
  )
