#!/bin/bash

# build load-balancer
cd ../load-balancer
mvn clean install

# build sample-app
cd ../load-balancer-sample-app
mvn clean install

# deploy docker containers
docker-compose build
docker-compose up -d
