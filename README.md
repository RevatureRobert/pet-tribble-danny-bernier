# Pet Tribble API
Like the pet rock, but a pet tribble!

## Instructions
Welcome to the pet tribble experiment. Here we are trying to experiment on tribbles and see if they could be a viable replacement for the pet rock!
If you need clarification on a tribble, you can look [here](https://en.wikipedia.org/wiki/Tribble). 

## Tech Stack
- Java version 8
- PostgreSQL
- JDBC
- Tomcat
- Servlet API
- REST

## Your job
We need you to construct a database of the tribbles we are currently researching and provide an api that we can use to work with that information. We would do it ourselves, but we are trying to control the population! 
In return for you hard work, we are willing to pay you in points! Here is how your contract will work:

## Scoring

### Database: 15 Points
Construct a database schema that has tables for our each tribble's information and for our labs that can hold the different tribbles. Considering how they multiply, we have had to construct multiple labs, so this is a must.
> For the relationships, each lab can have multiple Tribbles, but one tribble can only be held in one lab during its lifetime.

### Models: 10 Points
Create your Java models to correspond to the database tables. Each class should correspond to the tables and each field to their column.
> Keep credentials hidden in some fashion and submit the program with a sql script and an image of the ERD inside src/main/resources/sql directory

### JDBC: 25 Points
Connect to the Database Using the JDBC API. By the end of this step, you should have full CRUD capabilities for the models.

### Servlet: 10 Points
Add the servlet API to the project and create a custom servlet
> Either annotation or deployment descriptor regisration of servlet is acceptable.

### Endpoints: 5 Points Each
Create URL enpoints that correspond to CRUD operations on the models. These endpoints should follow RESTFul operations (resources and verbs). 
> Assume cascading delete for tribbles when we delete a lab. This is only in case of emergency where things get out of hand and we need to burn the lab down and everything goes with it. We tend to keep this covered up well so don't worry about keeping track of them and PETT regulations (People for the Ethical Treatment of Tribbles).

### Bonus
Pagination and html front end with/out frameworks is optional, but will be considered for bonus points.
