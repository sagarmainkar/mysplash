#!/bin/bash


if [ $# -lt 1 ]
  then
    echo "Please pass the name of microservice as an argument"
    exit 1
fi
echo "Deploying microservice deployment $1"

kubectl apply -f k8s/services/$1