import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "saarland.cispa.se"
version = "0.1"

plugins {
    scala
    id("com.github.maiflai.scalatest") version "0.25"
    kotlin("jvm") version "1.3.72"
    antlr
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    val scalaVersion = "2.12.10"
    implementation("org.scala-lang", "scala-library", scalaVersion)

    implementation("org.jetbrains.kotlin", "kotlin-compiler-embeddable", "1.3.21")
    implementation("org.log4s", "log4s_2.12", "1.8.2")
    runtimeOnly("ch.qos.logback", "logback-classic", "1.2.3")

    compileOnly("org.backuity.clist", "clist-macros_2.12", "3.5.1")
    compile("com.github.cretz.kastree", "kastree-ast-psi", "0.4.0")
    antlr("org.antlr:antlr4:4.8")
    
    implementation("com.lihaoyi", "fastparse_2.12", "2.1.3")
    implementation("org.backuity.clist", "clist-core_2.12", "3.5.1")
    implementation("org.json4s", "json4s-native_2.12", "3.6.7")
    implementation("com.github.tototoshi", "scala-csv_2.12", "1.3.6")
    implementation("com.github.pathikrit", "better-files_2.12", "3.8.0")

    implementation("org.scala-lang", "scala-compiler", scalaVersion)
    implementation("dk.brics", "automaton", "1.12-1")
    implementation("org.apache.commons", "commons-text", "1.7")

    testImplementation("org.scalatest", "scalatest_2.12", "3.0.8")
    testImplementation("org.scalacheck", "scalacheck_2.12", "1.14.0")
    // needed for the report generation in the scalatest gradle plugin
    testRuntime("org.pegdown", "pegdown", "1.6.0")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<ScalaCompile> {
    scalaCompileOptions.encoding = "UTF-8"
    scalaCompileOptions.isDeprecation = true
    scalaCompileOptions.isUnchecked = true
}

tasks.withType<Jar> {
    manifest.attributes["Main-Class"] = "saarland.cispa.se.tribble.Main"
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
