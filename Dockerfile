FROM openjdk:8

WORKDIR /dncontrol
RUN mkdir logs
EXPOSE 11037
COPY ./PersonajesSC/target/PersonajesSC-1-jar-with-dependencies.jar dncsrv
CMD [ "java", "-jar", "dncsrv" ]