FROM tomcat:9-jdk11

ENV USER=docker
### psi_ia user
ENV UID=2799
### pst_pub group
ENV GID=1137
RUN addgroup --gid "$GID" "$USER" \
  && adduser \
  --disabled-password \
  --gecos "" \
  --home "$(pwd)" \
  --ingroup "$USER" \
  --no-create-home \
  --uid "$UID" \
  "$USER"

ADD /target/psicquic-registry.war "/usr/local/tomcat/webapps/psicquic#registry.war"
RUN cp -r webapps.dist/ROOT webapps/
RUN cp -r webapps.dist/manager webapps/

RUN chown -R $USER:$USER /usr/local/tomcat

CMD ["catalina.sh", "run"]