# ── STAGE 1: Build with Maven ─────────────────────────────────────────────────
FROM maven:3.9.4-eclipse-temurin-21 AS builder

# set working dir
WORKDIR /app

# copy only pom to cache dependencies
COPY pom.xml .
# copy all source
COPY src ./src

# build jar (skip tests for faster image builds)
RUN mvn clean package -DskipTests

# ── STAGE 2: Package on a slim JRE ────────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine

# (optional) create a non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# where Spring writes temp files
VOLUME /tmp

# copy the jar from builder
COPY --from=builder /app/target/SHEUKVETE-0.0.1-SNAPSHOT.jar /app/app.jar

# expose the port your app listens on
EXPOSE 8081

# (optional) healthcheck using actuator endpoint
HEALTHCHECK --interval=30s --timeout=5s \
  CMD wget -qO- http://localhost:8081/admin/actuator/health || exit 1

# entrypoint
ENTRYPOINT ["java","-jar","/app/app.jar"]
