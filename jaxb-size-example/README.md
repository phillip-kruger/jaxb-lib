# Size Example.

This example marshal a very big file. 

The test case does:

1) a default marshal that fails due to `java.lang.OutOfMemoryError` exception  
2) a sax input marshal that manage to bind without the above exception

## Running the example (It's just a JUnit test)

```bash
mvn clean install
```