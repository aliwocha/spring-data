## **Device Rental Shop App**

The app is for managing a device rental shop.

Main features of the app:
* adding new device, deleting by its id
* adding new category, deleting by its id
* adding new client, deleting by its id
* renting a device based on client pesel and device id
* tracking the actual stock
* searching a device by its name (or part of a name)
* integration with H2 database

### **Technology used:**
* Java 11
* SpringBoot
* Hibernate
* H2

### **How to run:**
1. Clone the repository onto your own computer.

2. Go to the main folder of the project and run this command:

*for the Unix system:
```
./mvnw spring-boot:run
```
*for the Windows CMD:
```
mvnw.cmd spring-boot:run
```
3. Go to the following page in your browser to see database tables: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

4. Use default values to log in to H2 database:

   JDBC URL: *"jdbc:h2:mem:testdb"*

   username: *"sa"*

   password: *"[blank]"*
