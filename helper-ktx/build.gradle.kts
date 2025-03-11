plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("signing")
}

android {
    namespace = "io.github.libxposed.helper.kt"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }

    buildFeatures {
        resValues = false
        buildConfig = false
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles("proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf(
            "-Xno-param-assertions",
            "-Xno-call-assertions",
            "-Xno-receiver-assertions",
        )
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    compileOnly("androidx.annotation:annotation:1.5.0")
    compileOnly("io.github.decryptable:libxposed-api:1.0.0")
    implementation(project(":helper"))
}

publishing {
    publications {
        register<MavenPublication>("libxposed-helper") {
            artifactId = "libxposed-helper"
            group = "io.github.decryptable"
            version = "1.0.0"
            pom {
                name.set("libxposed-helper")
                description.set("Modern Xposed Helper")
                url.set("https://github.com/decryptable/libxposed-helper")
                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://github.com/decryptable/libxposed-helper/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        name.set("decryptable")
                        url.set("https://github.com/decryptable")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/decryptable/libxposed-helper.git")
                    url.set("https://github.com/decryptable/libxposed-helper")
                }
            }
            afterEvaluate {
                from(components.getByName("release"))
            }
        }
    }
    repositories {
        mavenLocal()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/decryptable/libxposed-helper")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}