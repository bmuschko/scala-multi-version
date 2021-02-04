# Scala Multi-Version Project

Demonstrates the use of different Scala versions in a multi-project Gradle build to compile, package, and execute the tests with the help of the plugin [prokod/gradle-crossbuild-scala](https://github.com/prokod/gradle-crossbuild-scala).

## Use Cases

* Project `a`: Builds with Scala 2.11 and 2.12.
* Project `b`: Only builds with Scala 2.11 and depends on outgoing 2.11-compatible artifact of project `a`.
* Project `c`: Builds with Scala 2.11 and 2.12 and depends on outgoing 2.11-compatible or 2.12-compatible artifact of project `a`.
* Project `d`: Builds with Scala 2.11 and publishes its artifact to a binary repository. The generated POM declares transitive dependencies.
* Project `e`: Builds with Scala 2.11 and 2.12 and defines a `Test` task that uses the Scala 2.11/2.12-compiled classes + test runtime dependencies to execute the tests.
* Project `f`: Builds an Android project and depends on the outgoing 2.11-compatible artifact of project `a`. Requires the Android plugin to be disabled to build properly in IntelliJ CE.

## Build Execution

The prokod/gradle-crossbuild-scala plugin introduces tasks for compiling the code and generating the JAR file per Scala version. For example, for Scala 2.11 you'd have a task named `compileCrossBuildScala_212Scala` and `crossBuildScala_212Jar`. The JAR task depends on the compilation task as it needs to generate the byte code to be bundled in the JAR file. The command line invocation below compiles and generates a JAR file for Scala 2.11 and 2.12 for the project `a` which is configured to support both Scala versions. 

```
$ ./gradlew :a:crossBuildScala_211Jar :a:crossBuildScala_212Jar --console=verbose
> Task :a:compileCrossBuildScala_211Java NO-SOURCE
> Task :a:compileCrossBuildScala_211Scala
> Task :a:processCrossBuildScala_211Resources NO-SOURCE
> Task :a:crossBuildScala_211Classes
> Task :a:crossBuildScala_211Jar
> Task :a:compileCrossBuildScala_212Java NO-SOURCE
> Task :a:compileCrossBuildScala_212Scala
> Task :a:processCrossBuildScala_212Resources NO-SOURCE
> Task :a:crossBuildScala_212Classes
> Task :a:crossBuildScala_212Jar
```

At this time, the plugin doesn't support similar functionality for executing tests. Project `e` introduces custom code to execute Scala version-specific `Test` tasks. Each `Test` task resolves the classes produces for a specific Scala version and the dedicated dependencies. The tasks are called `crossBuildScala_211Test` for Scala 2.11 and `crossBuildScala_212Test` for Scala 2.12.

```
$ ./gradlew :e:crossBuildScala_211Test :e:crossBuildScala_212Test --console=verbose
> Task :e:compileCrossBuildScala_211Java NO-SOURCE
> Task :e:compileCrossBuildScala_211Scala
> Task :e:processCrossBuildScala_211Resources NO-SOURCE
> Task :e:crossBuildScala_211Classes
> Task :e:crossBuildScala_211Test
> Task :e:compileCrossBuildScala_212Java NO-SOURCE
> Task :e:compileCrossBuildScala_212Scala
> Task :e:processCrossBuildScala_212Resources NO-SOURCE
> Task :e:crossBuildScala_212Classes
> Task :e:crossBuildScala_212Test
```

Executing the standard tasks like `build` will simply compile the code, generate the JAR, and execute the tests with the versions of Scala that are assigned to the configurations `implementation` and `testImplementation`. The prokod/gradle-crossbuild-scala plugin doesn't come into play at all. Therefore, Scala multi-version functionality is not supported. Probably the right thing to do would be to disable those standard tasks to avoid confusion.

## Open Issues

* [Defining a project dependency for a specific Scala version](https://github.com/prokod/gradle-crossbuild-scala/issues/101)
* [Support for executing tests with a specific Scala version](https://github.com/prokod/gradle-crossbuild-scala/issues/102)
* [Publishing functionality doesn't seem to declare dependencies in generated POM](https://github.com/prokod/gradle-crossbuild-scala/issues/103)
* [Current maintenance and development effort of plugin](https://github.com/prokod/gradle-crossbuild-scala/issues/104)
