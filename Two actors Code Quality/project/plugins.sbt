addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.8.0") // Static code quality measurement. Run 'sbt scalastyleGenerateConfig' for config file.

addSbtPlugin("com.sksamuel.scapegoat" %% "sbt-scapegoat" % "1.0.4") // Static code quality measurement

addSbtPlugin("org.brianmckenna" % "sbt-wartremover" % "0.14") // Static code quality measurement

addSbtPlugin("com.orrsella" % "sbt-stats" % "1.0.5") // Calculate number of files, lines and characters

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.3.5") // Calculate coverage