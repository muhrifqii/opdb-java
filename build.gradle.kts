plugins {
  java
  alias(libs.plugins.graalvm) apply false
}

allprojects {
  repositories {
    mavenCentral()
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}
