/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.compile.GroovyCompile
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test
import org.gradle.plugins.compile.AvailableJavaInstallations
import org.gradle.kotlin.dsl.*


// This file contains Kotlin extensions for the gradle/gradle build

fun Project.availableJavaInstallations(configure: AvailableJavaInstallations.() -> Unit): Unit =
    extensions.configure("availableJavaInstallations", configure)

val Project.availableJavaInstallations
    get() = extensions.getByName<AvailableJavaInstallations>("availableJavaInstallations")

