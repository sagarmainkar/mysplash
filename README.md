# MySplash Implementation

This is the implementation for the [Designing a system like Unsplash](https://medium.com/@sagarmainkar/designing-a-system-like-unsplash-com-part-i-3dc33e69aa63) article I published on [medium](https://medium.com/@sagarmainkar).

Please read the article to understand the design and then try the implmentation.

## Technologies 
- Microservices
- Java
- Spring Boot
- Kubernetes
- Maven
- Azure Cloud

> The main goal of the code is to create an understanding of
> using microservices and utilizing them to create an application.
> This is in no way a full-fledged production grade application 
> however it mimics the scenarios very well.

## Pre-Requisites
- You need to have [Minikube](https://minikube.sigs.k8s.io/docs/start/) installed
- You will need to have [Maven](https://maven.apache.org/) installed if you intend to build the code
- You need [Java 17](https://openjdk.org/)

## Usage
If you intend to deploy this application in your setup then you would need to do the following things:

- Git clone the repository
- Create a Azure account 
- In Azure cloud portal create a storage account "mysplashimages"
- In the storage account create a container named "myimages"
- Go to access keys and copy the "connectionstring"
- Go to base64encode.org and paste the copied connectionstring and copy the encoded value keep it a file
- On the base64encode.org encode the string "mysplashimages" and copy the encoded value to another file
- In the k8s/bootstrap/secrets.yml file replace the <ENCODED_CONNECTION_STRING> with your value
- In the k8s/bootstrap/secrets.yml file replace the <ENCODED_CONTAINER_NAME> with your value

### Using my docker hub containers
 If you do not want to build the code by yourself then it will be lot easier just follow these steps
 
- Create a minikube profile 
    > minikube start --memory=6144 --cpus=2 --kubernetes-version=v1.24.0 --vm-driver=virtualbox
- You can even directly  start minikube profile is not mandatory
- Enable ingress for minikube
    > minikube addons enable ingress
- Run the following script, ensure you have rights to run the script
    > ./create_resources.sh
- This should create all resources and deploy them in you minikube cluster in a namespace "mysplash"
- You can check the status with 
    > kubectl get pods -n mysplash
    > kubectl get svc -n mysplash
- Check the minikube IP
    > minikube IP
- Edit your /etc/hosts file and add the following, this way you don't have keep typing the IP address of minikube 
    > <minikubeip> mysplash.dev


 
 



