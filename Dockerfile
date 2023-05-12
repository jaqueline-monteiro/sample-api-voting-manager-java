FROM jenkins/jenkins:lts

USER root

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    rm -rf /var/lib/apt/lists/*

#macos
#ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-arm64

#windows
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64

ENV PATH ${JAVA_HOME}/bin:${PATH}

USER jenkins
