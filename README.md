# Scala Multi-Version Project

Demonstrates the use of multiple, parallel Scala versions in a multi-project Gradle build with the help of the plugin [prokod/gradle-crossbuild-scala](https://github.com/prokod/gradle-crossbuild-scala).

## Use Cases

* Project `a`: Builds with Scala 2.11 and 2.12.
* Project `b`: Only builds with Scala 2.11 and depends on outgoing 2.11-compatible artifact of project `a`.
* Project `c`: Builds with Scala 2.11 and 2.12 and depends on outgoing 2.11-compatible or 2.12-compatible artifact of project `a`.
* Project `d`: Builds with Scala 2.11 and publishes its artifact to a binary repository. The generated POM declares transitive dependencies.
* Project `e`: Builds with Scala 2.11 and 2.12 and defines a `Test` task that uses the Scala 2.11/2.12-compiled classes + test runtime dependencies to execute the tests.
* Project `f`: Builds an Android project and depends on outgoing 2.11-compatible artifact of project `a`. Requires the Android plugin to be disabled to build properly in IntelliJ CE.

## Open Issues

* [Defining a project dependency for a specific Scala version](https://github.com/prokod/gradle-crossbuild-scala/issues/101)
* [Support for executing tests with a specific Scala version](https://github.com/prokod/gradle-crossbuild-scala/issues/102)
* [Publishing functionality doesn't seem to declare dependencies in generated POM](https://github.com/prokod/gradle-crossbuild-scala/issues/103)
