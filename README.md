# EShop demo application

## How to build source code?

```bash
make
```

## How to run this demo?

```bash
docker run -d --name demo \
  -p 80:8080 \
  dodbrian-liveproject-tracing
```

## Test the demo

Use curl command or open url ```127.0.0.1/checkout``` in the browser. You should be able to see output as the followings.

```bash
➜  milestone1 git:(master) ✗ curl 127.0.0.1/checkout
You have successfully checked out your shopping cart.
```

## Run with Istio

### Install Istio

```bash
istioctl install --set profile=default --set values.pilot.traceSampling=100
```

### Install Jaeger add-on

```bash
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.17/samples/addons/jaeger.yaml
```

### Mark namespace for the sidercar injection

```bash
kubectl label namespace default istio-injection=enabled --overwrite=true
```

### Deploy Gateway and VirtualService

```bash
cd istio
kubectl apply -f manifest.yaml
```

### Forward port to access the application

```bash
kubectl port-forward service/istio-ingressgateway 8080:80 -n istio-system
```

### Call the application to generate traces

```bash
curl http://localhost:8080/checkout
```

### Open Jaeger UI

Jaeger UI address: http://localhost:8080/
