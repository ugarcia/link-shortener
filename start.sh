#!/bin/bash

echo "Killing old PostgreSQL processes"
# Kill existing PostgreSQL processes.
kill -9 $(pidof postgres) &>/dev/null

echo "Starting PostgreSQL"
# Start PostgreSQL server and output to log file.
./pgsql/bin/pg_ctl start -D ./pgsql/data -l pgsql/pg_out.log &>/dev/null

echo "Updating DB"
# Invoke migration over created database
java -jar link-shorten.jar db migrate link-shorten.yml &>/dev/null

echo "Starting Server at http://localhost:8080"
# Start Jetty server etc. with App configuration.
java -jar link-shorten.jar server link-shorten.yml
