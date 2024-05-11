package main.planets.data.remote.service

import SolarSystemKMP.composeApp.BuildConfig
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class PlanetsRemoteService() {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        install(Logging){
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.d("HTTP Client", null, message)
                }
            }
            level = LogLevel.HEADERS
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header("X-RapidAPI-Key", value = BuildConfig.apiKey)
            header("X-RapidAPI-Host", value = "planets-info-by-newbapi.p.rapidapi.com")
        }
    }.also { Napier.base(DebugAntilog()) }

    suspend fun getPlanets(): HttpResponse {
        return client.get {
            url("https://planets-info-by-newbapi.p.rapidapi.com/api/v1/planets/")
        }
    }

}