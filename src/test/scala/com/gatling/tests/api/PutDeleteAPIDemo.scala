package com.gatling.tests.api

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PutDeleteAPIDemo extends Simulation{
  //Protocol
  val httpProtocol = http
    .baseUrl("https://reqres.in/api")

  //Scenario
  val scn1 = scenario("Create User")
    .exec(
      http("Create User Req")
        .post("/users")
        .asJson
        .body(StringBody(
          """
            |{
            |    "name": "morpheus",
            |    "job": "leader"
            |}
            |""".stripMargin))
        .check(
          status.is(201),
          jsonPath("$.name").is("morpheus"))
    )
  //Setup
  setUp(
    scn1.inject(rampUsers(5).during(5))
  ).protocols(httpProtocol)

}
