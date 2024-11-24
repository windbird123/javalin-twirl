package com.github.windbird.web

import io.javalin.Javalin
import io.javalin.config.JavalinConfig
import io.javalin.http.Context
import io.javalin.http.staticfiles.Location
import org.slf4j.{Logger, LoggerFactory}
import play.twirl.api.StringInterpolation


object Server {
	lazy val logger: Logger = LoggerFactory.getLogger(getClass)

	def home(ctx: Context): Unit = {
		ctx.html(html.index("2024").toString)
	}

	def comment(ctx: Context): Unit = {
		val text = ctx.queryParamAsClass("text", classOf[String]).get
		ctx.html(html"<p>echo: $text</p>".toString)
	}

	def main(args: Array[String]): Unit = {
		val app: Javalin = Javalin.create((config: JavalinConfig) => {
				config.requestLogger.http((context, ms) => {
					logger.info(s"Requests: ${context.req.getRequestURI}")
				})

				config.staticFiles.add(staticFiles => {
					staticFiles.hostedPath = "/assets"
					staticFiles.directory = "/assets"
					staticFiles.location = Location.CLASSPATH
					staticFiles.precompress = false
				})

				// available at /webjars/name/version/file.ext
				config.staticFiles.enableWebjars()
			})
			.get("/", home)
			.get("/comment", comment)
			.start(8080)
	}
}
