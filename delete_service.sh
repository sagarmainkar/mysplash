#!/bin/bash


if [ $# -lt 1 ]
  then
    echo "Please pass the name of microservice as an argument"
    exit 1
fi
echo "Deleting microservice deployment $1"

kubectl delete -f k8s/services/$1