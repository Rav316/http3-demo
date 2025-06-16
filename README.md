# Spring Boot HTTP3 connection

empty application with Netty server configuration for HTTP3 connection

## How to run application?

execute command:
```shell
./gradlew bootRun
```

## How to test server operation?

The launch instructions are on the [following site](https://serverpilot.io/docs/guides/apps/http3/)

Run this command to test the server's performance and see the response time to a request:
```shell
./curl --connect-timeout 2 --http3-only -k -o /dev/null -s -w "Time to first byte: %{time_starttransfer}s\nTotal time: %{time_total}s\n" https://localhost:8080/hello
```
