package com.github.windbird.web

import io.javalin.Javalin
import io.javalin.config.JavalinConfig
import io.javalin.http.Context
import io.javalin.http.staticfiles.Location
import org.slf4j.{Logger, LoggerFactory}
import play.twirl.api.StringInterpolation

import scala.util.Random

case class Goal(id: String, text: String)

object Server {
	lazy val logger: Logger = LoggerFactory.getLogger(getClass)

	def home(ctx: Context): Unit = {
		val name: String = ctx.queryParamAsClass("name", classOf[String]).getOrDefault("")
		ctx.html(html.home(name).toString)
	}

	def template(ctx: Context): Unit = {
		ctx.html(html.template("my_message").toString)
	}

	def htmxTest(ctx: Context): Unit = {
		val query: String = ctx.queryParamAsClass("query", classOf[String]).getOrDefault("")
		ctx.html(html.htmx_test(goals, query).toString)
	}

	def alpinejsTest(ctx: Context): Unit = {
		ctx.html(html.alpinejs_test().toString)
	}

	def goal(ctx: Context): Unit = {
		val text = ctx.formParamAsClass("goal", classOf[String]).getOrDefault("")
		if (text.nonEmpty) {
			val id = Random.alphanumeric.filter(_.isLetter).take(6).mkString
			goals = Goal(id, text) +: goals
			Thread.sleep(1000)
			ctx.html(share.html.goal_row(id, text).toString)
		} else {
			ctx.html("")
		}
	}

	def goal_delete(ctx: Context): Unit = {
		val id: String = ctx.pathParamAsClass("id", classOf[String]).get
		goals = goals.filter(_.id != id)
	}

	var goals: Seq[Goal] = List.empty

	def main(args: Array[String]): Unit = {
		val app: Javalin = Javalin.create((config: JavalinConfig) => {
				config.requestLogger.http((context, ms) => {
					logger.info(s"Request: ${context.req.getRequestURI} took ${ms}ms")
				})

				config.staticFiles.add(staticFiles => {
					staticFiles.hostedPath = "/public"
					staticFiles.directory = "/public"
					staticFiles.location = Location.CLASSPATH
					staticFiles.precompress = false
				})

				// available at /webjars/name/version/file.ext
				config.staticFiles.enableWebjars()
			})
			.get("/", home)
			.get("/template", template)
			.get("/htmx-test", htmxTest)
			.get("/alpinejs-test", alpinejsTest)
			.post("/goal", goal)
			.delete("/goal/{id}", goal_delete)
			.start(8080)
	}
}
