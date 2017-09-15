# VIA-MW Appgrid Service Docker deployment image
# MAINTAINER Accedo.tv
FROM openjdk

ADD /build/libs/restExercise-1.0-SNAPSHOT.jar /restexercise.jar

EXPOSE 8090

CMD ["java", "-jar", "/restexercise.jar"]