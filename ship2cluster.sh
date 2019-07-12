# Copy spark application into all workers's app folder
sudo cp /home/ubuntu/Documents/Recommender/target/scala-2.11/FBID-Recomendador-assembly-0.1.0.jar /mnt/spark-apps

# Copy the file to be processed to all workers's data folder

# Worker 1 Validations
docker exec -ti spark-worker-1 ls -l /opt/spark-apps

#Creating some variables to make the docker run command more readable
#App jar environment used by the spark-submit image
SPARK_APPLICATION_JAR_LOCATION="/opt/spark-apps/FBID-Recomendador-assembly-0.1.0.jar"
#App main class environment used by the spark-submit image
SPARK_APPLICATION_MAIN_CLASS="org.mvb.applications.Recommender"

#We have to use the same network as the spark cluster(internally the image resolves spark master as spark://spark-master:7077)
docker run --network docker-spark-cluster_spark-network \
-v /mnt/spark-apps:/opt/spark-apps \
--env SPARK_APPLICATION_JAR_LOCATION=$SPARK_APPLICATION_JAR_LOCATION \
--env SPARK_APPLICATION_MAIN_CLASS=$SPARK_APPLICATION_MAIN_CLASS \
spark-submit:2.3.1
