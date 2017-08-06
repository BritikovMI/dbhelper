#!/usr/bin/env bash

echo $@

HOME_DIR=/c/Users/BritikovMI/dbhelper
HOME_DIR1=/c/Users/BritikovMI/.m2/repository

CLPATH=${HOME_DIR}/dbhelper-jpa/target/dbhelper-jpa-1.0-SNAPSHOT.jar
CLPATH=${CLPATH}:${HOME_DIR1}/com/github/noraui/ojdbc7/12.1.0.2/ojdbc7-12.1.0.2.jar

echo ${CLPATH}

java -classpath $CLPATH ru.rbt.dbhelper.testing.OrderServiceTest $@

sleep 3m