plugins {
    id("com.android.library")
    id("maven-publish")
    id("signing")
}

android {
    namespace = "io.github.libxposed.helper"
    compileSdk = 35
    buildToolsVersion = "35.0.0"

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
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

publishing {
    publications {
        register<MavenPublication>("libxposed-helper") {
            artifactId = "libxposed-helper"
            group = "io.github.decryptable"
            version = "1.0.4"
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



dependencies {
    compileOnly("androidx.annotation:annotation-experimental:1.3.0")
    compileOnly("androidx.annotation:annotation:1.5.0")
    compileOnly("io.github.decryptable:libxposed-api:+")
}
