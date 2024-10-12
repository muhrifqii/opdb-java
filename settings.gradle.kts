pluginManagement {
    includeBuild("gradle/build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}


rootProject.name = "one-piece-database"
include("scrapper")
