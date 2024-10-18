FROM jenkins/jenkins:lts

USER root

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    rm -rf /var/lib/apt/lists/*

RUN JAVA_HOME=$(readlink -f /usr/bin/java | sed "s:bin/java::")

ENV JAVA_HOME $JAVA_HOME

ENV PATH ${JAVA_HOME}/bin:${PATH}

USER jenkins
