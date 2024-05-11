package main.planets.data.local.mapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import main.planets.data.local.model.PlanetEntity
import main.planets.domain.domain.Planet


fun PlanetEntity.mapToDomainModel(): Planet {
    return Planet(
        id = this.id,
        planetId = this.planetId,
        name = this.name,
        description = this.description,
        img = this.img,
        imgDescription = this.imgDescription,
        mass = this.mass,
        volume = this.volume,
        planetOrder = this.planetOrder,
        source = this.source,
        wikiLink = this.wikiLink
    )
}

fun Planet.mapFromDomainModel(): PlanetEntity {
    return PlanetEntity(
        id = this.id,
        planetId = this.planetId,
        name = this.name,
        description = this.description,
        img = this.img,
        imgDescription = this.imgDescription,
        mass = this.mass,
        volume = this.volume,
        planetOrder = this.planetOrder,
        source = this.source,
        wikiLink = this.wikiLink
    )
}

fun List<PlanetEntity>.mapToDomainModelList(): List<Planet> {
    return this.map { it.mapToDomainModel() }
}

fun List<Planet>.mapFromDomainModelList(): List<PlanetEntity> {
    return this.map { it.mapFromDomainModel() }
}

fun Flow<List<PlanetEntity>>.mapToDomainModelListFlow(): Flow<List<Planet>> {
    return this.map { it.mapToDomainModelList() }
}

