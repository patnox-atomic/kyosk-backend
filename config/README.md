## Solution

I Have implemented HTTP services using Java/SpringBoot. The code resides in config folder.

#### Java/SpringBoot code

<a href="pom.xml">Maven Project</a>

<a href="README.md">config documentation</a>

#### Docker Compose Files (SpringBoot App + Postgres DB)

<a href="../docker-compose/">manifests files</a>

<a href="../docker-compose/README.md">docker compose documentation</a>

#### Minikube deployment manifests files location

<a href="../minikube-deployment/">manifests files</a>

<a href="../minikube-deployment/README.md">minikube documentation</a>

##### Dockerfile location and documentation

<a href="./Dockerfile"> Dockerfile location</a>


#############################################################
# Technical specification
## API Methods: 

| Name        | Method      | URL                                                  |
| ---         | ---         | ---                                                  |
| List        | `GET`       | `/configs`                                           |	done
| Create      | `POST`      | `/configs`                                           |	done
| Get         | `GET`       | `/configs/{name}`                                    |	done
| Update      | `PUT/PATCH` | `/configs/{name}`                                    |	done
| Delete      | `DELETE`    | `/configs/{name}`                                    |	done
| Query       | `GET`       | `/search?name={config_name}&data.{key}={value}`      |	done
| HealthCheck | `GET`       | `/healthcheck`                                       |	done


#### Expected Output:

Application is up and running

### Unit Test cases

<a href="src/test/java/com/patnox/config/">Test Case files</a>

#### TEST Expected Output: 

```sh
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11.783 s
[INFO] Finished at: 2021-10-14T20:06:27+03:00
[INFO] ------------------------------------------------------------------------
```

#### Build App

```sh
./mvnw clean package
```

#### Run Tests

```sh
./mvnw clean test
```

#### Run App

```sh
./mvnw clean spring-boot:run
```

#### Docker

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

Run on Kubernetes

Either using compose file directly

```sh
kompose --file docker-compose.yml up
```

Or

```sh
kompose --file docker-compose.yml convert --volumes hostPath
kubectl apply -f combined-deployment.yaml
```





