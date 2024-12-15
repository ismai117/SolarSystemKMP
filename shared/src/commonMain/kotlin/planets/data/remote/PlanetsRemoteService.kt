package planets.data.remote

import DEV_SERVER_HOST
import io.ktor.client.HttpClient
import io.ktor.http.encodedPath
import kotlinx.rpc.krpc.ktor.client.installRPC
import kotlinx.rpc.krpc.ktor.client.rpc
import kotlinx.rpc.krpc.ktor.client.rpcConfig
import kotlinx.rpc.krpc.serialization.json.json
import kotlinx.rpc.withService
import planets.domain.domain.Planet
import planets.domain.service.PlanetsService

class PlanetsRemoteService() {

    private val client by lazy {
        HttpClient {
            installRPC()
        }
    }

    suspend fun getPlanets(): List<Planet> {
        return service().getPlanets() ?: emptyList()
    }

    private suspend fun service(): PlanetsService {
        return client.rpc {
            url {
                host = DEV_SERVER_HOST
                port = 8080
                encodedPath = "/api"
            }

            rpcConfig {
                serialization {
                    json()
                }
            }
        }.withService()
    }

}
