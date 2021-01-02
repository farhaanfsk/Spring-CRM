# Spring-CRM

A Simple Spring MVC application to demo Model, View and Controller pattern and integrated with Hibernate for persisting the data to DB (only MYSQL)

To Build the Application and generate the `.war` file execute the following command:
```
docker-compose -f Build.yml up
```
The above command will genearate the target and the `.war ` file to it.

To run the application execute the following command:
```
docker-compose up -d --build
```
navigate to `localhost:8080/HibernateMVC` to use the application
