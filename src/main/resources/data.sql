INSERT INTO users (user_id,name,vat,counter_id,address,post_code,phone,date_of_birth,balance)
SELECT * FROM CSVREAD('classpath:data/user_balance_init.csv');



INSERT INTO authorized_personnel (name,username,password,role)
SELECT * FROM CSVREAD('classpath:data/operators_admins_init.csv');