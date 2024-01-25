import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "ru.perm.v"
// change version on publishing
version = "0.24.0125"
description = "Shop Kafka receiver"
val kafkaApiVersion = "3.3.1"

buildscript {
	var kotlinVersion: String? by extra; kotlinVersion = "1.1.51"

	repositories {
		mavenCentral()
	}

	dependencies {
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
	}

}

repositories {
	mavenCentral()
	maven {

		url = uri("http://v.perm.ru:8082/repository/ru.perm.v") //OK
		isAllowInsecureProtocol = true
		credentials {
			username = System.getenv("NEXUS_CRED_USR") ?: extra.properties["nexus-ci-username"] as String?
			password = System.getenv("NEXUS_CRED_PASS") ?: extra.properties["nexus-ci-password"] as String?
		}
	}
	gradlePluginPortal()
}

plugins {
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
	id("maven-publish")
	kotlin("kapt") version "1.7.0"
	idea
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.kafka:spring-kafka")
// EXAMPLE FOR KAFKA STREAM
//	implementation("org.apache.kafka:kafka-streams")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

publishing {
	repositories {
		maven {
			url = uri("http://v.perm.ru:8082/repository/ru.perm.v/")
			isAllowInsecureProtocol = true
			//  publish в nexus "./gradlew publish" из ноута и Jenkins проходит
			// export NEXUS_CRED_USR=admin
			// echo $NEXUS_CRED_USR
			credentials {
				username = System.getenv("NEXUS_CRED_USR")
				password = System.getenv("NEXUS_CRED_PSW")
			}
		}
	}
	publications {
		register("mavenJava", MavenPublication::class) {
			from(components["java"])
		}
	}
}

// use ./gradlew bootRun
springBoot {
	mainClass.set("ru.perm.v.shopkotlin.kafka_receiver.ShopKafkaReceiverApp")
}
