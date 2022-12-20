#!/bin/bash
export IP_ADDR=$(hostname -f)
yum update -y
yum install java-17-amazon-corretto-devel -y
yum install git -y
wget https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
tar -xvf apache-maven-3.6.3-bin.tar.gz
mv apache-maven-3.6.3 /opt/
M2_HOME='/opt/apache-maven-3.6.3'
PATH="$M2_HOME/bin:$PATH"
export PATH
git clone https://github.com/moniami/yellowfin-frontend
cd yellowfin-frontend
mvn clean install
mvn spring-boot:run