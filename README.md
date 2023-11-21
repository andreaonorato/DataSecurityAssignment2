## Getting Started

## About
This is a code is an implementation for token based authentcation mechanism after which RMI(remote method invocation) based invocation of printing service is done

Following are the steps to run this project in local environment

## Step 1
install latest version of jdk

## Step 2
install Mysql workbench and database and add user: "root" and password: "94NkIBdasNKtyO0" 

## Step 3
Execute following command in root databsed of mysql workbench<br/>
a. create database (db_name)<br/>
b. use (db_name)<br/>
c. create table users (username VARCHAR(255) NOT NULL PRIMARY KEY, password VARCHAR(255) NOT NULL, token VARCHAR(255))<br/>
d. create records for username and passwords of all users in the users in Acl.txt file in users table in mysql workbench.<br/>

## Step 3
Run CreateUser.java class file to add user to the database (please change the username each time you run this file in function main() as username is assigned a primary key constraint and a not null constraint and therefore is unique for each user )

## Step 4
under prototype1 folder
Run PrintServer.java file to boot print server
Run AuthServer.java file to boot authentication server
Finally run Client.java file

similarly above steps can be repeated for same files under prototype2 folder

each protype has a Client, PrintServer,AuthServer to compare all the scenarios of the problem statement.

# NOTE:
Results of All the operations invoked on the respective client are printed on console of respective (print/auth) server which is running for respective prototype folder 

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
