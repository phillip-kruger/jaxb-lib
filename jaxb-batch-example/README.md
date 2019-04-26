# Batch Example.

This example marshal and unmarshal to and from Object to XML in a loop

## Running the example

```bash
mvn "-Dexec.args=-classpath %classpath com.github.phillipkruger.jaxblib.example.PlainJavaApplication" -Dexec.executable=/usr/lib/jvm/java-openjdk/bin/java process-classes org.codehaus.mojo:exec-maven-plugin:1.5.0:exec
```

This will prompt you for input:

```bash
INFO: Welcome to the JAXB test.
Enter to :
 1 = Run bad batch example
 2 = Run good batch example
 x = Exit
```

The 'bad' example takes much longer than the 'good' example