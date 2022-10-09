#!/bin/bash

kubectl delete -f ./k8s/services/imageservice/deployment.yml
kubectl delete -f ./k8s/services/users/deployment.yml
kubectl delete -f ./k8s/bootstrap/postgres/pgadmin-dep.yml
kubectl delete -f ./k8s/bootstrap/zipkin/statefulset.yml
kubectl delete -f ./k8s/bootstrap/zipkin/service.yml
kubectl delete -f ./k8s/bootstrap/rabbitmq/statefulset.yml
kubectl delete -f ./k8s/bootstrap/rabbitmq/configmap.yml
kubectl delete -f ./k8s/bootstrap/rabbitmq/rbac.yml
kubectl delete -f ./k8s/bootstrap/rabbitmq/services.yml
kubectl delete -f ./k8s/bootstrap/postgres/service.yml
kubectl delete -f ./k8s/bootstrap/postgres/statefulset.yml
kubectl delete -f ./k8s/bootstrap/postgres/volume.yml
kubectl delete -f ./k8s/bootstrap/postgres/configmap.yml
kubectl delete -f ./k8s/bootstrap/secrets.yml
kubectl delete -f ./k8s/bootstrap/ingress/ingress.yml
kubectl delete -f ./k8s/bootstrap/namespace.yml









