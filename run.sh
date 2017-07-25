#!/usr/bin/env bash

echo $@

HOME_DIR=/c/Users/BritikovMI/dbhelper

CLPATH=${HOME_DIR}/dbhelper-utils/target/dbhelper-utils-1.0-SNAPSHOT.jar

echo ${CLPATH}

java -classpath $CLPATH ru.rbt.dbhelper.utils.Main $@

sleep 3m