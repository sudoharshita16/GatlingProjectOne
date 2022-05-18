package com.gatling.tests.api

//import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class APITest1 extends Simulation {
  //protocol
  val httpProtocol = http
    .baseUrl(url = "https://reqres.in/api/users")

  //scenario
  val scn = scenario("Get API Request Demo")
    .exec(
      http("Get single user")
        .get("/2")
        // Response Assertions
        .check(
          status.is(200),
          jsonPath("$.data.first_name").is("Janet"))
        )

    .pause(1)

  //setup
  setUp(
    scn.inject(rampUsers(10).during(5))
      .protocols(httpProtocol)
  )
}

