rootProject.name = "openapi-test"

include(":generated-kt")

pluginManagement {

    val kotlinVersion = "1.3.70"
    val openapiGeneratorVersion = "4.2.3"

    plugins {
        kotlin("jvm") version kotlinVersion apply false
        id("org.openapi.generator") version openapiGeneratorVersion apply false
    }

    repositories {
        mavenLocal()
        jcenter()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://jitpack.io")
    }

}
