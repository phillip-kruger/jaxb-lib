# Concurrency Example.

This example marshal and unmarshal to and from Object and XML via a REST Endpoint using siege to generate load (concurrency)

## Running the example

Start the thorntail server with
```bash
mvn thorntail:run
```

Then run the siege script to generate some load:

```bash
./siege_bad.sh
```

```bash
./siege_good.sh
```

Compare the 'bad' example to the 'good' example and you can see the difference in concurrent transactions