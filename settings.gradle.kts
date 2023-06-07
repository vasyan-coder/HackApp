pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HackApp"
include(":app")
include(":core")
include(":feature_welcome:presentation")
include(":feature_login:presentation")
include(":feature_login:domain")
include(":data")
include(":navigation")
include(":feature_registration:presentation")
include(":feature_registration:domain")
