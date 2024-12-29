package planets.data.service

import planets.domain.domain.Planet

class PlanetsService() {

    fun getPlanets(): List<Planet> {
        return planets
    }

    fun getPlanetById(planetId: Int): Planet? {
        return planets.find { it.planetId == planetId }
    }

}

private val planets = listOf(
    Planet(
        id = 1,
        planetId = 1,
        name = "Mercury",
        description = "Mercury is the smallest planet in the Solar System and the closest to the Sun. Its orbit around the Sun takes 87.97 Earth days, the shortest of all the Sun's planets.",
        img = "https://upload.wikimedia.org/wikipedia/commons/4/4a/Mercury_in_true_color.jpg",
        imgDescription = "Mercury in true color (by MESSENGER in 2008)",
        mass = "3.3011 x 10^23 kg",
        volume = "6.083 x 10^10 km^3",
        planetOrder = 1,
        source = "Wikipedia",
        wikiLink = "https://en.wikipedia.org/wiki/Mercury_(planet)"
    ),
    Planet(
        id = 2,
        planetId = 2,
        name = "Venus",
        description = "Venus is the second planet from the Sun and is named after the Roman goddess of love and beauty. As the brightest natural object in Earth's night sky after the Moon, Venus can cast shadows and can be visible to the naked eye in broad daylight.",
        img = "https://upload.wikimedia.org/wikipedia/commons/0/08/Venus_from_Mariner_10.jpg",
        imgDescription = "False colour composite of Venus in visual and ultraviolet wavelengths (from Mariner 10). The surface is completely obscured by clouds.",
        mass = "4.8675 x 10^24 kg",
        volume = "9.2843 x 10^11 km^3",
        planetOrder = 2,
        source = "Wikipedia",
        wikiLink = "https://en.wikipedia.org/wiki/Venus"
    ),
    Planet(
        id = 3,
        planetId = 3,
        name = "Earth",
        description = "Earth is the third planet from the Sun and the only astronomical object known to harbor life. While large amounts of water can be found throughout the Solar System, only Earth sustains liquid surface water. About 71% of Earth's surface is made up of the ocean, dwarfing Earth's polar ice, lakes, and rivers.",
        img = "https://upload.wikimedia.org/wikipedia/commons/c/cb/The_Blue_Marble_%28remastered%29.jpg",
        imgDescription = "A photograph of Earth taken by the crew of Apollo 17 in 1972. A processed version became widely known as The Blue Marble.",
        mass = "5.97237 x 10^24 kg",
        volume = "1.08321 x 10^12 km^3",
        planetOrder = 3,
        source = "Wikipedia",
        wikiLink = "https://en.wikipedia.org/wiki/Earth"
    ),
    Planet(
        id = 4,
        planetId = 4,
        name = "Mars",
        description = "Mars is the fourth planet from the Sun and the second-smallest planet in the Solar System, being larger than only Mercury. In English, Mars carries the name of the Roman god of war and is often called the \"Red Planet\".",
        img = "https://upload.wikimedia.org/wikipedia/commons/0/02/OSIRIS_Mars_true_color.jpg",
        imgDescription = "Pictured in natural color in 2007",
        mass = "6.4171 x 10^23 kg",
        volume = "1.63118 x 10^11 km^3",
        planetOrder = 4,
        source = "Wikipedia",
        wikiLink = "https://en.wikipedia.org/wiki/Mars"
    ),
    Planet(
        id = 5,
        planetId = 5,
        name = "Jupiter",
        description = "Jupiter is the fifth planet from the Sun and the largest in the Solar System. It is a gas giant with a mass more than two and a half times that of all the other planets in the Solar System combined, but slightly less than one-thousandth the mass of the Sun.",
        img = "https://upload.wikimedia.org/wikipedia/commons/2/2b/Jupiter_and_its_shrunken_Great_Red_Spot.jpg",
        imgDescription = "Full disk view in natural colour, taken by the Hubble Space Telescope in April 2014",
        mass = "1.8982 x 10^27 kg",
        volume = "1.4313 x 10^15 km^3",
        planetOrder = 5,
        source = "Wikipedia",
        wikiLink = "https://en.wikipedia.org/wiki/Jupiter"
    ),
    Planet(
        id = 6,
        planetId = 6,
        name = "Saturn",
        description = "Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine and a half times that of Earth. It has only one-eighth the average density of Earth; however, with its larger volume, Saturn is over 95 times more massive.",
        img = "https://upload.wikimedia.org/wikipedia/commons/c/c7/Saturn_during_Equinox.jpg",
        imgDescription = "Pictured in natural color approaching equinox, photographed by Cassini in July 2008; the dot in the bottom left corner is Titan.",
        mass = "8.2713 x 10^14 km^3",  // NOTE: This seems like an error in the data - Mass should be in kilograms.
        volume = "8.2713 x 10^14 km^3", // Same for volume - it should also be in km^3.
        planetOrder = 6,
        source = "Wikipedia",
        wikiLink = "https://en.wikipedia.org/wiki/Saturn"
    ),
    Planet(
        id = 7,
        planetId = 7,
        name = "Uranus",
        description = "Uranus is the seventh planet from the Sun. Its name is a reference to the Greek god of the sky, Uranus, who, according to Greek mythology, was the great-grandfather of Ares, grandfather of Zeus and father of Cronus. It has the third-largest planetary radius and fourth-largest planetary mass in the Solar System.",
        img = "https://upload.wikimedia.org/wikipedia/commons/4/48/Uranus_as_seen_by_NASA%27s_Voyager_2_%28remastered%29.png",
        imgDescription = "Photograph of Uranus in true colour (by Voyager 2 in 1986)",
        mass = "(8.6810±0.0013) x 10^25 kg",
        volume = "6.833 x 10^13 km^3",
        planetOrder = 7,
        source = "Wikipedia",
        wikiLink = "https://en.wikipedia.org/wiki/Uranus"
    ),
    Planet(
        id = 8,
        planetId = 8,
        name = "Neptune",
        description = "Neptune is the eighth and farthest-known Solar planet from the Sun. In the Solar System, it is the fourth-largest planet by diameter, the third-most-massive planet, and the densest giant planet. It is 17 times the mass of Earth, and slightly more massive than its near-twin Uranus.",
        img = "https://upload.wikimedia.org/wikipedia/commons/6/63/Neptune_-Voyager_2%2829347980845%29_flatten_crop.jpg",
        imgDescription = "Photograph taken by NASA's Voyager 2 in 1989",
        mass = "1.02413 x 10^26 kg",
        volume = "6.253 x 10^13 km^3",
        planetOrder = 8,
        source = "Wikipedia",
        wikiLink = "https://en.wikipedia.org/wiki/Neptune"
    )
)