# EventService
Steps to Run the projeect

Ensure the SQL server is up and Running 
Specify the DB Pproperies in the the application.properties
\src\main\resources\application.properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.jpa.show-sql=
spring.jpa.properties.hibernate.format_sql = 

Import Project in STS
Run as Spring Boot Project .The project runs in default Port 8080
You can choose port by sepcifying attribute in \src\main\resources\application.properties
server.port=8443
