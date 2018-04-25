#!/bin/bash


cd labo03-SMTP/;
mvn clean install;

java -jar target/labo03-SMTP-1.0-SNAPSHOT-standalone.jar;