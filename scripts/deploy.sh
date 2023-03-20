#!/bin/sh

az spring app deploy -n greeter-a --source-path . --build-env BP_JVM_VERSION=17 --env SPRING_APPLICATION_NAME=greeter-a --no-wait

az spring app deploy -n greeter-b --source-path . --build-env BP_JVM_VERSION=17 --env SPRING_APPLICATION_NAME=greeter-b --no-wait