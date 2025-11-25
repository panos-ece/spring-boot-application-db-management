A web application which exposes a credit store system.
The credit store consists of a webservice serving a REST API that allows:
- reading user information with no access restrictions 
- requesting increments or decrements for a user's balance given the right operator credentials are provided
- adding new or removing admins/operators given the right admin credentials are provided

and a User Interface in HTML where anyone can see all users and their balance.

All users start with 100 Euro balance, and no balance can go below 0.

Audit logs that tell which operator requested which increment or decrement and when.

Audit logs that tell which admin added or removed an admin or operator and when.

## Notes
The [user_balance_init.csv](src/main/resources/user_balance_init.csv) file, represents a dummy user-base and their balance information.

The [operators_admins_init.csv](src/main/resources/operators_admins_init.csv) file, represents the credentials and the role level of a particular user. 

These files used for initialization of the storage.

## How to Run
You can run this repo by using `./mvnw spring-boot:run` from the parent directory of the repo.

Also, there is a [pwc_postman_collections](pwc_postman_collections)  in order to manual test the functionality in Postman tool.