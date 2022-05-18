package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ComputerDataBase extends Simulation {

	val httpProtocol = http
		.baseUrl("https://computer-database.gatling.io")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-GB,en-US;q=0.9,en;q=0.8")
		.doNotTrackHeader("1")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36")





	val scn = scenario("ComputerDataBase")
		.exec(http("ComputersDatabasePage")
			.get("/computers"))
		.pause(8)
		.exec(http("NewComputersPage")
			.get("/computers/new"))
		.pause(38)
		.exec(http("CreateNewComputer")
			.post("/computers")

			.formParam("name", "MyComputerHaru")
			.formParam("introduced", "2010-01-01")
			.formParam("discontinued", "2020-01-01")
			.formParam("company", "2"))
		.pause(23)
		.exec(http("FilterComputer")
			.get("/computers?f=MyComputerHaru"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}