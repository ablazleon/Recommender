FROM ubuntu:16.04
MAINTAINER abl

COPY /home/ubuntu/Documents/Recommender/target/scala-2.11/FBID-Recomendador-assembly-0.1.0.jar lib/FBID-Recomendador-assembly-0.1.0.jar
COPY /home/ubuntu/Downloads/jdk-8u211-linux-x64.tar.gz lib/jdk-8u211-linux-x64.tar.gz
COPY ./docker-entrypoint.sh /

#RUN mkdir /home/data
RUN mkdir /home/spark_warehouse

COPY /home/ubuntu/Documents/Recommender/Dataset/artist_alias.txt data/artist_alias.txt
COPY /home/ubuntu/Documents/Recommender/Dataset/artist_data.txt data/artist_data.txt
COPY /home/ubuntu/Documents/Recommender/Dataset/user_artist_data.txt data/user_artist_data.txt

RUN tar zfx /home/ubuntu/Downloads/jdk-8u211-linux-x64.tar.gz  -C /home/ubuntu/Downloads/ \
    && rm -rf $JAVA_HOME/man \
    && rm /usr/local/jdk-8u211-linux-x64.tar.gz
RUN apt-get update \
 && apt-get install -y iputils-ping \
 && apt-get install -y curl

RUN chmod +x /docker-entrypoint.sh

ENV JAVA_HOME="/usr/local/jdk1.8.0_151"
ENV PATH=$JAVA_HOME/bin:$PATH

ENTRYPOINT ["/docker-entrypoint.sh"]
