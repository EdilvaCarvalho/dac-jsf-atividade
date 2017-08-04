FROM payara/micro
COPY target/dac-jsf-pratica-1.0-SNAPSHOT.war dac-jsf-pratica-1.0-SNAPSHOT.war
CMD ["java", "-jar", "payara-micro.jar", "--deploy", "dac-jsf-pratica-1.0-SNAPSHOT.war"]