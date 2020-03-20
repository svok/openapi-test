import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    kotlin("jvm")
    id("org.openapi.generator")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    create<GenerateTask>("generateKotlinModels") {
        val genPackage = "${project.group}.rest"
        group = "openapi"
        val spec = "$rootDir/spec.yaml"
        val dest = "$rootDir/generated-kt"
        inputs.files(spec)
        outputs.files(fileTree(dest))
        generatorName.set("kotlin-server")
        inputSpec.set(spec)
        outputDir.set(dest)
        modelPackage.set("$genPackage.models")
        apiPackage.set("$genPackage.apis")
        packageName.set(genPackage)
        invokerPackage.set("$genPackage.infrastructure")
        generateModelDocumentation.set(true)
        generateModelTests.set(true)
        configOptions.putAll(
            mutableMapOf(
//                "modelMutable" to "true",
                "swaggerAnnotations" to "true"
            )
        )
//        systemProperties.putAll(
//            mapOf(
//                "models" to "",
//                "modelTests" to "",
//                "modelDocs" to "",
//                "apis" to "",
//                "apiTests" to "",
//                "apiDocs" to ""
//            )
//        )
    }
    val cleanKotlinModels by creating(Delete::class) {
        group = "openapi"
        val modelsKt = project(":generated-kt").projectDir
        fileTree(modelsKt).visit {
            if (file.name !in arrayOf(
                    "build.gradle",
                    ".openapi-generator-ignore"
                )) {
                delete(file)
            }
        }
    }

}
