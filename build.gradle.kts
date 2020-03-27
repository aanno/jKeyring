val javaVersion = "11"

plugins {
    id("org.javamodularity.moduleplugin") version "1.6.0"
    `java-library`
    `maven-publish`
    idea
    eclipse
    // kotlin("jvm") version "1.3.70"
    `java-library-distribution`

    // Plugin which checks for dependency updates with help/dependencyUpdates task.
    id("com.github.ben-manes.versions") version "0.28.0"
    // Plugin which can update Gradle dependencies, use help/useLatestVersions
    id("se.patrikerdes.use-latest-versions") version "0.2.13"
}

group = "com.github.aanno.jkeyring"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

sourceSets.create("modules") {
    java {
        srcDir("keyring/src")
        srcDir("keyring.fallback/src")
        srcDir("keyring.impl/src")
    }
}

dependencies {
    // implementation(kotlin("stdlib-jdk8"))
    api("org.slf4j", "slf4j-api", "1.7.0")
    api("com.google.code.findbugs", "jsr305", "3.0.2")

    runtimeOnly("org.slf4j", "slf4j-jdk14", "1.7.0")

    // implementation("net.java.dev.jna", "jna", "5.5.0")
    implementation("net.java.dev.jna", "jna", "4.5.2")
    // implementation("net.java.dev.jna", "jna-platform", "5.5.0")

    // needed to find service provider
    api("org.atteo.classindex", "classindex", "3.4")
    annotationProcessor("org.atteo.classindex", "classindex", "3.4")


    // testApi("junit", "junit", "4.12")

    // JUnit 5
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.3.1")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.3.1")
    testRuntimeOnly("org.junit.platform", "junit-platform-console", "1.6.0")

    // Kotlintest
    // testImplementation("io.kotlintest", "kotlintest-core", "3.4.2")
    // testImplementation("io.kotlintest", "kotlintest-assertions", "3.4.2")
    // testImplementation("io.kotlintest", "kotlintest-runner-junit5", "3.4.2")
}

java {
    // withJavadocJar()
    withSourcesJar()
}

idea {
    module {
        setDownloadJavadoc(true)
        setDownloadSources(true)
    }
}

eclipse {
    classpath {
        setDownloadJavadoc(true)
        setDownloadSources(true)
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
tasks {
    withType<JavaCompile> {
        doFirst {
            options.compilerArgs.addAll(listOf(
                "--release", "11",
                "-deprecation", "-Xlint:all"
                // "--add-exports=java.xml/com.sun.org.apache.xerces.internal.parsers=com.github.aanno.dbtoolchain"
                // , "--add-modules jnr.enxio"
                // , "-cp", "jnr-enxio-0.19.jar"
                // , "--add-modules", "ALL-MODULE-PATH",
                // , "--module-path", classpath.asPath
            ) /* + moduleJvmArgs + patchModule */)
            println("Args for for ${name} are ${options.allCompilerArgs}")
        }
    }
    wrapper {
        distributionType = Wrapper.DistributionType.ALL
        version = "6.2.2"
    }
    /*
    compileKotlin {
        kotlinOptions.jvmTarget = javaVersion
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = javaVersion
    }
     */
    // https://stackoverflow.com/questions/50128728/how-do-i-use-the-native-junit-5-support-in-gradle-with-the-kotlin-dsl
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform()
    }

    jar {
        manifest {
            attributes(
                mapOf(
                    "Main-Class" to "com.github.aanno.imap2signal.MailFetch",
                    "Class-Path" to configurations.runtimeClasspath
                        .get().map{ file -> "lib/" + file.getName()}
                        .joinToString(" ")
                )
            )
        }

        dependsOn("generatePomFileForMavenPublication")
        // from("$buildDir/publications/maven/pom-default.xml")
        // into("META-INF/maven/${group}/${name}")

        into("META-INF/maven/${project.group}/${project.name}") {
            from("$buildDir/publications/maven/pom-default.xml")
            rename(".*", "pom.xml")
        }
    }
}



publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}


/*
tasks.named("build") {
    dependsOn("distTar")
}
*/
