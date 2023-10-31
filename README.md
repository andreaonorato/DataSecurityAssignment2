## Getting Started

## About
This is a code is an implementation for token based authentcation mechanism after which RMI(remote method invocation) based invocation of printing service is done

Followinf are the steps to run this project in local environment

## Step 1
install latest version of jdk

## Step 2
install Mysql workbench and database and add user: "root" and password: "94NkIBdasNKtyO0"

## Step 3
Run CreateUser.java class file to add user to the database (please change the username each time you run this file in function main() as username is assued to be unique for each user )

## Step 4
Run PrintServer.java file to boot print server
Run AuthServer.java file to boot authentication server
Finally run Client.java file

# NOTE:
Results of All the operations invoked of the client are printed on console of respective server which is running 

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
