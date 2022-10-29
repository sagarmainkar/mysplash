#!/bin/bash

kubectl apply -f ./k8s/bootstrap/namespace.yml
kubectl apply -f ./k8s/bootstrap/secrets.yml
kubectl apply -f ./k8s/bootstrap/ingress/ingress.yml
kubectl apply -f ./k8s/bootstrap/postgres/configmap.yml
kubectl apply -f ./k8s/bootstrap/postgres/pgadmin-dep.yml
kubectl apply -f ./k8s/bootstrap/postgres/service.yml
kubectl apply -f ./k8s/bootstrap/postgres/statefulset.yml
kubectl apply -f ./k8s/bootstrap/postgres/volume.yml
kubectl apply -f ./k8s/bootstrap/rabbitmq/configmap.yml
kubectl apply -f ./k8s/bootstrap/rabbitmq/rbac.yml
kubectl apply -f ./k8s/bootstrap/rabbitmq/services.yml
kubectl apply -f ./k8s/bootstrap/rabbitmq/statefulset.yml
kubectl apply -f ./k8s/bootstrap/zipkin/service.yml
kubectl apply -f ./k8s/bootstrap/zipkin/statefulset.yml
kubectl apply -f ./k8s/services/imageservice/deployment.yml
kubectl apply -f ./k8s/services/users/deployment.yml
kubectl apply -f ./k8s/services/imgprocessor/deployment.yml