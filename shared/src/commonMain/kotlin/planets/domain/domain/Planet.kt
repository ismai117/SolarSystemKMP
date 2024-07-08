package planets.domain.domain

data class Planet(
    val id: Int?,
    val planetId: Int,
    val name: String,
    val description: String,
    val img: String,
    val imgDescription: String,
    val mass: String,
    val volume: String,
    val planetOrder: Int,
    val source: String,
    val wikiLink: String
)
