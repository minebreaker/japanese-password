#!/bin/bash

if [[ -z "$1" ]]; then
    echo "Jar file name is not given."
    exit 1
fi

if [[ -z "${PROJECT_HOME}" ]]; then
    JAR="./build/libs/"$1""
else
    JAR=""${PROJECT_HOME}"/build/libs/"$1""
fi
echo "Input JAR path: "${JAR}""

native-image \
    --no-fallback \
    -jar "${JAR}" \
    --gc=epsilon \
    -H:IncludeResources="^(version|commit)$"
