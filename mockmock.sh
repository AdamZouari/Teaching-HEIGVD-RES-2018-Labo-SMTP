#!/bin/bash
echo "building image server ..."

docker build -t mockmock .

echo "running server in deamon ...  "

docker run -d -p $1:$1 -p $2:$2 mockmock -p $1 -h $2


