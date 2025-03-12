pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.library") version "7.4.1"
        id("org.jetbrains.kotlin.android") version "1.7.20"
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // mavenLocal()

        maven {
            name = "GitHubPackages libxposed-api"
            url = uri("https://maven.pkg.github.com/decryptable/libxposed-api")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: ""
                password = System.getenv("GITHUB_TOKEN") ?: ""
            }
        }
    }
}
rootProject.name = "helper"
include(":helper", ":helper-ktx")
