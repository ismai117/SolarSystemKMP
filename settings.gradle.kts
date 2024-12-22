rootProject.name = "SolarSystemKMP"
include(":composeApp")
include(":shared")
include(":server")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
