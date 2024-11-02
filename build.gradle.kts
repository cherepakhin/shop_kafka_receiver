import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "ru.perm.v"
// change version on publishing
version = "0.24.0217.1"
description = "Shop Kafka Consumer"
val kafkaApiVersion = "3.3.1"
var shopKotlinExtDtoVersion = "0.0.5"

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
		url = uri("https://plugins.gradle.org/m2/")
	}
	maven {

		url = uri("http://192.168.1.20:8081/repository/ru.perm.v") //OK
		isAllowInsecureProtocol = true
		credentials {
// export NEXUS_CRED_USR=admin
// echo $NEXUS_CRED_USR
//			username = System.getenv("NEXUS_CRED_USR") ?: extra.properties["nexus-ci-username"] as String?
// export NEXUS_CRED_PSW=pass
// echo $NEXUS_CRED_PSW
//			password = System.getenv("NEXUS_CRED_PSW") ?: extra.properties["nexus-ci-password"] as String?
			username = "admin"
			password = "pass"
		}
	}
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
	// FOR USE TOMCAT
//	implementation("org.springframework.boot:spring-boot-starter-web")
	// FOR USE JETTY
	implementation("org.springframework.boot:spring-boot-starter-web") {
		exclude("org.springframework.boot:spring-boot-starter-tomcat")
	}
	implementation("org.springframework.boot:spring-boot-starter-jetty")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.kafka:spring-kafka")

	implementation("ru.perm.v:shop_kotlin_extdto:$shopKotlinExtDtoVersion")

// prometheus - metrics
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.micrometer:micrometer-registry-prometheus")


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
			url = uri("http://192.168.1.20:8081/repository/ru.perm.v") //OK
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
		create<MavenPublication>("maven"){
			artifact(tasks["bootJar"])
		}
	}
}

// use ./gradlew bootRun
springBoot {
	mainClass.set("ru.perm.v.shopkotlin.kafka_consumer.ShopKafkaConsumerApp")
}
