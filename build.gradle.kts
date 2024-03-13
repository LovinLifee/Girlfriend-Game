plugins {
    id("java")
    id("application")
    id("io.freefair.lombok") version "8.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("de.gurkenlabs:litiengine:0.8.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass = "net.avuna.rae.Main"
}

tasks.test {
    useJUnitPlatform()
}