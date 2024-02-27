plugins {
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "5.8.0"
  kotlin("plugin.spring") version "1.9.20"
}

configurations {
  testImplementation { exclude(group = "org.junit.vintage") }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webflux")

  // AWS modules
  implementation("software.amazon.awssdk:s3:2.24.6")
  implementation("software.amazon.awssdk:sqs:2.3.2")

  // junit modules
  testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
}

kotlin {
  jvmToolchain(21)
}
repositories {
  mavenCentral()
}

tasks {
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = "21"
    }
  }
}
