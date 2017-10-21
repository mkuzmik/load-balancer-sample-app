# Load Balancer Sample App

## Overview
This project contains an sample application to demonstrate load-balancer library.

## How to run

*Important note: You should have https://github.com/mkuzmik/load-balancer project set up before.*

This project require a few tool to run:
- Java 8
- Maven
- Docker
- Docker Compose

If you are using Windows or Mac, you will additionally need:
- VirtualBox
- Docker Machine

Firstly (for Mac and Windows only), we need to create virtual machine for docker:

```
# run this command only once
docker-machine create --driver virtualbox --virtualbox-cpu-count "1" --virtualbox-memory "2048" docker-load-balancer
```
Remember, when you create docker-load-balancer machine, it remains on you computer until you delete it. You need
to run above command only once.

Once docker-machine is created, we should start it:
```
# run this command every time after restart of your computer
docker-machine start docker-load-balancer
# run this command every time after opening new terminal
eval $(docker-machine env docker-load-balancer)
```

Then build an current version of this project:
```
# run this command after any changes in application code
mvn  clean install
```

Once you app is successfully built, we are ready to setup containers:
```
# run these commands every time you want to restart an application
docker-compose build
docker-compose up -d
```

You can check IP address of you docker machine by command:
```
docker-machine ip docker-load-balancer
```

Finito! You can access your app by typing in you browser:
```
<your docker-machine ip address>:8080
# for example: 192.168.99.100:8080
```

## Consuming an application

Not available yet...

## Connecting to databases

Requirements:
- Postgres

```
psql -h <docker machine ip address> -p <database port> -U postgres
# for example: psql -h 192.168.99.100 -p 5432 -U postgres
```

Then you can type SQL directly to database.

## Authors
- Mateusz Kuźmik
- Jakub Kacorzyk
- Bartłomiej Łazarczyk
- Szymon Jakóbczyk
