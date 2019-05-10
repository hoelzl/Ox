import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.31"
    id("org.openjfx.javafxplugin") version "0.0.7"
    application
    id("org.jetbrains.dokka") version "0.9.18"
    id("org.beryx.jlink") version "2.9.4"
}


val myVersion = "0.1.0-SNAPSHOT"
val myApplicationName = "OxApp"
val myMainClassName = "ox.OxApp"
val myLauncherName = "ox"

application {
    group = "com.xantira"
    version = myVersion
    applicationName = myApplicationName
    mainClassName = myMainClassName
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.4.2")
    testImplementation("org.hamcrest:hamcrest:2.1")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("PASSED", "FAILED", "SKIPPED")
            showExceptions = true
            showStackTraces = true
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_12
    targetCompatibility = JavaVersion.VERSION_12
}

javafx {
    version = "12.0.1"
    modules("javafx.controls", "javafx.fxml")
}

tasks.withType<DokkaTask> {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"
}

jlink {
    launcher {
        name = myLauncherName
    }
    addExtraDependencies("javafx")
    options.addAll("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
}
