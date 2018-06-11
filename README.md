
# XeniumNFV (This Project Moved to Gitlab and not Supported on Microsoft Github anymore !)

https://gitlab.com/nephh/XeniumNFV (Become Private Repository will be available on august 2018!)

ToDo (2018.6.11)
+ Add Veth pairs support (For traffic Shaping and SFlow Agent support) currently support Path Peer -> Critical
+ Add KVM api support (KVM Machines as VNFs), currently only support Docker containers as VNF
+ Add QOS support on GUI
# Extreme Network Orchestration Web Application
![alt text](https://img.shields.io/badge/container-ready-green.svg)
[![](https://images.microbadger.com/badges/image/nephilimboy/xenonetapp.svg)](https://microbadger.com/images/nephilimboy/xenonetapp "Get your own image badge on microbadger.com")
[![](https://images.microbadger.com/badges/version/nephilimboy/xenonetapp.svg)](https://microbadger.com/images/nephilimboy/xenonetapp "Get your own version badge on microbadger.com")


## Feature

1. Easy SDN Network Diagram Creator

2. Creating custom VNF’s and Host’s

3. Add Unlimited Main Server

4. Human resource management

5. Using SSH to Communicate between Main Servers

6. Written in java So it can integrate with ODL

7. Can run on any server (Windows or linux) as master Node

8. Powered by Spring Framework and Hibernate as ORM 

9. MySQL DB 

10. Angular 4.3 JS Framework

## Before development Build Steps
1. install java 1.8.xxx
2. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.
3. [Yarn][]: We use Yarn to manage Node dependencies.
   Depending on your system, you can install Yarn either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

    yarn install

We use yarn scripts and [Webpack][] as our build system.
[Yarn][] is also used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in [package.json](package.json). You can also run `yarn update` and `yarn install` to manage dependencies.
Add the `help` flag on any command to see how you can use it. For example, `yarn help update`.

The `yarn run` command will list all of the scripts available to run for this project.

### Using angular-cli

You can also use [Angular CLI][] to generate some custom client code.

For example, the following command:

    ng generate component my-component

will generate few files:

    create src/main/webapp/app/my-component/my-component.component.html
    create src/main/webapp/app/my-component/my-component.component.ts
    update src/main/webapp/app/app.module.ts

## Development Build

	./mvnw clean -Pdev -Pwebpack

## Prod Build

	To optimize the XeniumNFV application for production, run:

    ./mvnw -Pprod clean package

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.
	
## Ui Build

	yarn start


## Docker Build

	You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw package -Pprod docker:build

Then run:

    docker-compose -f src/main/docker/app.yml up -d


