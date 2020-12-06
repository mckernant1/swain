import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    kotlin("jvm") version "1.4.20"
    kotlin("plugin.serialization") version "1.4.20"
    id("com.github.johnrengelman.shadow") version "5.1.0"

}

group = "com.github.mckernant1.lol.swain"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "com.github.mckernant1.lol.swain.RunnerKt"
}

repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/kotlinx") }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("com.github.mckernant1:lol-esports-api-wrapper:0.1.17")
    implementation("com.beust:klaxon:5.4")
    implementation("org.apache.logging.log4j:log4j-api:2.11.1")
    implementation("org.apache.logging.log4j:log4j-core:2.11.1")

}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}


tasks.withType<ShadowJar>() {
    manifest {
        attributes["Main-Class"] = "RunnerKt"
    }
}
