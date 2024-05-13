package main.planets.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetDetailDto(
    @SerialName("alternativeName")
    val alternativeName: String? = null,
    @SerialName("aphelion")
    val aphelion: Int? = null,
    @SerialName("argPeriapsis")
    val argPeriapsis: Double? = null,
    @SerialName("aroundPlanet")
    val aroundPlanet: String? = null,
    @SerialName("avgTemp")
    val avgTemp: Int? = null,
    @SerialName("axialTilt")
    val axialTilt: Double? = null,
    @SerialName("bodyType")
    val bodyType: String? = null,
    @SerialName("density")
    val density: Double? = null,
    @SerialName("dimension")
    val dimension: String? = null,
    @SerialName("discoveredBy")
    val discoveredBy: String? = null,
    @SerialName("discoveryDate")
    val discoveryDate: String? = null,
    @SerialName("eccentricity")
    val eccentricity: Double? = null,
    @SerialName("englishName")
    val englishName: String? = null,
    @SerialName("equaRadius")
    val equaRadius: Double? = null,
    @SerialName("escape")
    val escape: Double? = null,
    @SerialName("flattening")
    val flattening: Double? = null,
    @SerialName("gravity")
    val gravity: Double? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("inclination")
    val inclination: Double? = null,
    @SerialName("isPlanet")
    val isPlanet: Boolean? = null,
    @SerialName("longAscNode")
    val longAscNode: Double? = null,
    @SerialName("mainAnomaly")
    val mainAnomaly: Double? = null,
    @SerialName("mass")
    val massDto: MassDto,
    @SerialName("meanRadius")
    val meanRadius: Double? = null,
    @SerialName("moons")
    val moonsDto: List<MoonDto>? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("perihelion")
    val perihelion: Int? = null,
    @SerialName("polarRadius")
    val polarRadius: Double? = null,
    @SerialName("semimajorAxis")
    val semimajorAxis: Int? = null,
    @SerialName("sideralOrbit")
    val sideralOrbit: Double? = null,
    @SerialName("sideralRotation")
    val sideralRotation: Double? = null,
    @SerialName("vol")
    val volDto: VolDto
)

@Serializable
data class MoonDto(
    @SerialName("moon")
    val moon: String? = null,
    @SerialName("rel")
    val rel: String? = null
)

@Serializable
data class MassDto(
    @SerialName("massExponent")
    val massExponent: Int? = null,
    @SerialName("massValue")
    val massValue: Double? = null
)

@Serializable
data class VolDto(
    @SerialName("volExponent")
    val volExponent: Int? = null,
    @SerialName("volValue")
    val volValue: Double? = null
)