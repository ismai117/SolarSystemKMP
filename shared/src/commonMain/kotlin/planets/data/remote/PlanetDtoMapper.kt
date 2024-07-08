package planets.data.remote

import planets.domain.domain.Planet


fun PlanetDto.mapToDomainModel(): Planet {
    return Planet(
        id = null,
        planetId = this.id,
        name = this.name,
        description = this.description,
        img = this.imgSrc.img,
        imgDescription = this.imgSrc.imgDescription,
        mass = this.basicDetails.mass,
        volume = this.basicDetails.volume,
        planetOrder = this.planetOrder,
        source = this.source,
        wikiLink = this.wikiLink
    )
}

fun List<PlanetDto>.mapToDomainModelList(): List<Planet> {
    return this.map { it.mapToDomainModel() }
}