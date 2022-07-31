FROM postgres

ADD https://github.com/betty2310/FOMATEM .
COPY sql/* .

RUN createdb football_manager \
    && psql -d football_manager -f createDB.sql \
    psql -d football_manager -f function_trigger.sql \
    sql -d football_manager -f view.sql

COPY data/* .
RUN psql -d football_manager -f getData.sql

FROM platpus/javafx
FROM openjdk:11

ADD https://github.com/betty2310/FOMATEM/releases/download/v1.2.0/App.jar .

ENTRYPOINT [ "java", "-jar", "App.jar", "--add-modules=javafx.controls,javafx.fxml"]
