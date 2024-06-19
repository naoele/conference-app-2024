package io.github.droidkaigi.confsched.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidRoborazziPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("io.github.takahirom.roborazzi")
                apply("com.google.devtools.ksp")
            }
            android {
                testOptions {
                    unitTests {
                        all {
                            it.jvmArgs("-noverify")
                            it.systemProperties["robolectric.graphicsMode"] = "NATIVE"
                            it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
                            it.maxParallelForks = Runtime.getRuntime().availableProcessors()
                        }
                    }
                }
            }
            dependencies {
                testImplementation(libs.library("androidxTestEspressoEspressoCore"))
                testImplementation(libs.library("junit"))
                testImplementation(libs.library("robolectric"))
                testImplementation(libs.library("androidxTestExtJunit"))
                testImplementation(libs.library("roborazzi"))
                testImplementation(libs.library("roborazziCompose"))
            }
        }
    }
}
