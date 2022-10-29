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
- Once the Postgres pod is in running state execute the followint command
    > kubectl exec -it postgres-0 -n mysplash -- psql -U mysplash
- You would have entered the postgres microservice. Now you need to create databases. The following commands are also listed in the file mysplash_setup.sql
    > \c mysplash;
    > CREATE DATABASE images;
    > CREATE DATABASE users;
    > CREATE DATABASE imgprocess;

- Sometimes the imageservice,imgprocessor and the users service go in the error state as they do not find the DB required ,if this happens then after creating the DB's you can delete and recreate these three services by running the following script
    > ./delete_service.sh <service_name> example
    > ./delete_service.sh imageservice
    > ./deploy_service.sh <service_name>
    > Following are service names imageservice, users and imgprocessor
    
- Check the minikube IP
    > minikube IP
- Edit your /etc/hosts file and add the following, this way you don't have keep typing the IP address of minikube 
    > <minikubeip> mysplash.dev


 ### Postman API
 To test your setup you will need to run API's that I have created in Postman in the following order
 - Create user and note the userid in the response
 - Upload a image for the userid received in the response while creatng a user
 - Check the Azure Storage for image upload
 - Check logs of individual microservices
 - Check the DB 
 
Here is the collection of APIs

 [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/80469-a02106ed-fb1f-4f6c-b1e6-d9257e245a65?action=collection%2Ffork&collection-url=entityId%3D80469-a02106ed-fb1f-4f6c-b1e6-d9257e245a65%26entityType%3Dcollection%26workspaceId%3D4666577f-9db4-4c3a-aeb3-9558e628dac5#?env%5Bmysplash_kube%5D=W3sia2V5IjoiaG9zdCIsInZhbHVlIjoibXlzcGxhc2guZGV2IiwiZW5hYmxlZCI6dHJ1ZSwidHlwZSI6ImRlZmF1bHQiLCJzZXNzaW9uVmFsdWUiOiJteXNwbGFzaC5kZXYiLCJzZXNzaW9uSW5kZXgiOjB9XQ==)
 



