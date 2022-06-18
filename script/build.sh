#!/bin/bash

echo Build subscriber jar
cd ../subscriber
mvn clean install -DskipTests

cd ../producer
mvn clean install -DskipTests