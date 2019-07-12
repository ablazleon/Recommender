#!/usr/bin/env bash

cd ..

echo '*****************************'
echo 'Compiling Scala project...'
echo '*****************************'
sbt clean assembly

echo '*********************'
echo 'Copying libraries...'
echo '*********************'
rm docker/lib/FBID-Recomendador-assembly-0.1.0.jar
cp /home/ubuntu/Documents/Recommender/target/scala-2.11/FBID-Recomendador-assembly-0.1.0.jar docker/lib/FBID-Recomendador-assembly-0.1.0.jar

cd docker

echo '*************************'
echo 'Building Docker image...'
echo '*************************'
docker build . --tag=recommenderd