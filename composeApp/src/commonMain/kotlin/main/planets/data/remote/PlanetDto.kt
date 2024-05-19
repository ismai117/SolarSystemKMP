package main.planets.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetDto(
    @SerialName("basicDetails")
    val basicDetails: BasicDetails,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("imgSrc")
    val imgSrc: ImgSrc,
    @SerialName("name")
    val name: String,
    @SerialName("planetOrder")
    val planetOrder: Int,
    @SerialName("source")
    val source: String,
    @SerialName("wikiLink")
    val wikiLink: String
)

@Serializable
data class BasicDetails(
    @SerialName("mass")
    val mass: String,
    @SerialName("volume")
    val volume: String
)

@Serializable
data class ImgSrc(
    @SerialName("img")
    val img: String,
    @SerialName("imgDescription")
    val imgDescription: String
)