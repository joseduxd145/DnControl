FROM openjdk:8

WORKDIR /dncontrol
EXPOSE 11037
COPY ./PersonajesSC/target/PersonajesSC-1-jar-with-dependencies.jar PersonajesSC
CMD [ "java", "-jar", "PersonajesSC" ]