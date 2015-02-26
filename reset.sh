#!/bin/bash

# Remove existing PostgreSQL data.
rm -rf pgsql/data

# Kill existing PostgreSQL processes.
kill -9 $(pidof postgres) &>/dev/null

echo "Setting up PostgreSQL"

# Init PostgreSQL necessary files.
./pgsql/bin/pg_ctl init -D ./pgsql/data &>/dev/null

# Start PostgreSQL server and output to log file, setting timeout for it.
./pgsql/bin/pg_ctl start -D ./pgsql/data -l pgsql/pg_out.log &>/dev/null

# Create App database.
./pgsql/bin/psql -d postgres -c 'CREATE DATABASE "link-shorten"' &>/dev/null
RES=$?
COUNT=0

# Time wait for server start.
while [ "$RES" -ne "0" ] && [ "$COUNT" -ne 5 ]; do
    ./pgsql/bin/psql -d postgres -c 'CREATE DATABASE "link-shorten"' &>/dev/null
    RES=$?
    COUNT=$((COUNT+1))
    sleep 2
done

if [ "$COUNT" -eq 5 ]; then
    echo "Could not setup PostgreSQL"
    exit 1
fi

# Create App User, give it enough provileges for now.
./pgsql/bin/psql -d "link-shorten" -c 'CREATE USER albion WITH SUPERUSER;' &>/dev/null

echo "Creating Database"

# Invoke migration over created database
java -jar link-shorten.jar db migrate link-shorten.yml &>/dev/null

# Stop PostgreSQL server.
./pgsql/bin/pg_ctl stop -D ./pgsql/data &>/dev/null

echo "Installation succesfull, run ./start.sh to start the server."
