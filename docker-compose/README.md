# HTTP service deployment using docker compose
HTTP service is dockerized and ready to get deployed using docker compose

#### Docker Compose

To create an image from our Dockerfile, we need to run ‘docker build':

```sh
$> docker build --tag=patnox/kyosk_service:v1.0 .
```

and then to run the container from our image:

```sh
$> docker run -p8096:8096 patnox/kyosk_service:v1.0
```

Pull postgres image

```sh
docker pull postgres:14
```

Run Docker Compose

```sh
docker-compose up
```

Rebuild Docker and restart app

```sh
cd docker-compose
docker-compose down
docker rmi patnox/kyosk_service:v1.0
docker-compose up
```

Check Status

```sh
docker images
docker ps -a
docker image rm d191afba1bb1
```

Convert to kubernetes deployment

```sh
kompose --file docker-compose.yml convert --volumes hostPath
```

