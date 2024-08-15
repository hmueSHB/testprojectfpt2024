# Use an official Tomcat 9 image with JRE 8 as a base image
FROM tomcat:9-jre8

# Copy the WAR file to the Tomcat webapps directory
COPY /target/testprojectfpt2024-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

# Expose port 8080
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
