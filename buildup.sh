#!/bin/bash
kubectl delete -f k8s/billing.yaml -f k8s/delivery.yaml -f k8s/eshop.yaml -f k8s/inventory.yaml -f k8s/logistics.yaml
#kubectl delete -f k8s/jaeger.yaml

make
kind load docker-image dodbrian-liveproject-tracing

#kubectl apply -f k8s/jaeger.yaml
kubectl apply -f k8s/billing.yaml -f k8s/delivery.yaml -f k8s/eshop.yaml -f k8s/inventory.yaml -f k8s/logistics.yaml
