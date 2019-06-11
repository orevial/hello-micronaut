FROM oracle/graalvm-ce:1.0.0-rc15 as graalvm
COPY . /home/app/hello-micronaut
WORKDIR /home/app/hello-micronaut
RUN native-image --no-server -cp build/libs/hello-micronaut-*.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/hello-micronaut .
ENTRYPOINT ["./hello-micronaut"]
