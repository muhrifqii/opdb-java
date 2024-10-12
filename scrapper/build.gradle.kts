plugins {
  java
  alias(libs.plugins.graalvm)
}

group = "com.muhrifqii.opdb"
version = "0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(23))
  }
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

dependencies {
  testImplementation(libs.junit.jupiter)
  annotationProcessor(libs.lombok)
  compileOnly(libs.lombok)

  implementation(libs.picocli)
  implementation(libs.jsoup)
  implementation(libs.gson)
  implementation(libs.diffutils)
  annotationProcessor(libs.picocli.codegen)
}

tasks.withType<JavaCompile> {
	val compilerArgs = options.compilerArgs
	compilerArgs.add("-Aproject=${project.group}/${project.name}")
}

graalvmNative {
  binaries {
    named("main") {
      imageName.set("opdb-scrapper")
      mainClass.set("com.muhrifqii.scrapper.OpdbScrapApp")
      requiredVersion.set("23")
      buildArgs.addAll("--verbose", "--enable-url-protocols=http,https")
      // configurationFileDirectories.from(file("src/main/resources/config"))
    }
  }
}
