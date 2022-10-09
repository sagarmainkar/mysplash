#!/bin/sh

mvn clean package -P build-docker-image

./create_resources.sh