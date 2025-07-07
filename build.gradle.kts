plugins {
    java
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.33.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    testCompileOnly("org.projectlombok:lombok:1.18.36")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.36")
    testImplementation("io.qameta.allure:allure-junit5:2.22.0")
    implementation("org.apache.logging.log4j:log4j-core:2.24.3")
    implementation("org.apache.logging.log4j:log4j-api:2.24.3")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.3")
    runtimeOnly("org.aspectj:aspectjweaver:1.9.21")
}

tasks.test {
    useJUnitPlatform()
    ignoreFailures = false
    finalizedBy("allureReport")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

allure {
    version.set("2.22.0")
    adapter {
        frameworks {
            junit5 {
                adapterVersion.set("2.22.0")
            }
        }
    }
}