FROM postgres:latest
ENV POSTGRES_USER user
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB db
COPY /init.sql /docker-entrypoint-initdb.d/
EXPOSE 5432

#docker build -t <tagname> -f Dockerfile . -> from the same folder is Dockerfile
#docker run -d -p 5432:5432 <container name>
#docker exec -it <container name> psql -U user -W db
#enter password
#SELECT * FROM pg_catalog.pg_tables WHERE schemaname='public';
