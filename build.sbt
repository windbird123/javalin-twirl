val scala3Version = "3.5.2"

lazy val root = project
	.in(file("."))
	.enablePlugins(SbtTwirl)
	.settings(
		name := "javalin-twirl",
		version := "0.1.0-SNAPSHOT",

		scalaVersion := scala3Version,

		libraryDependencies ++= Seq(
			"io.javalin" % "javalin" % "6.3.0",
			"org.slf4j" % "slf4j-simple" % "2.0.16",
			"com.augustnagro" %% "magnum" % "1.3.0",
			"com.zaxxer" % "HikariCP" % "5.0.1",
			"mysql" % "mysql-connector-java" % "8.0.33",
			"org.playframework.twirl" %% "twirl-api" % "2.0.7",
			"org.webjars.npm" % "bootstrap" % "5.3.3",
			"org.webjars.npm" % "alpinejs" % "3.14.3"
		)
	)
