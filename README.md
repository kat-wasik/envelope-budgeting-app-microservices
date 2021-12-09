# Intro

This repository represents backend part of a project that was intended as an envelope budgeting app. Envelope budgeting is a simple method of planning your spending by putting cash in envelopes. Each signed envelope represent a category of expenditure (e.g. groceries, clothing, subscriptions, car insurance etc.). Since it traditionally involves cash, it helps you make sure you don't spend money you don't actually have. However, the method can be also used while emulating envelopes by having multiple bank accounts (one for every category of spending) or by creating virtual categories and assigning money to them (on paper or by using a budgeting app).

The intended functionality of this project was heavily inspired by YNAB (You Need A Budget) mobile app, but packaged as a mobile first web app. The initial idea was to let users create budgets (and potentially share those budgets between multiple users e.g. partners), add their accounts (e.g. banks accounts, cash wallets) and register transactions. After registering transaction user would decide how to create (one or multiple) entries for this transactions and assign those entries to spending categories. Separating categories entries from transactions would let user to use the app as a reliable spending tracker, since all transactions registered in the app would mirror what would be shown in bank statements, while also creating a possibility of putting one transaction in more than one category (e.g. for those times when you pay once in a big store, but part of what you bought are groceries and the rest you would like to categorize as toiletries etc.).

What you see here is my first attempt to create a full stack web application using Spring and Angular. Since tracking spending is an important part of envelope budgeting method, but can also be a stand-alone functionality, it was decided to create a spending tracker as a stepping stone to building an envelope budgeting app, a minimum viable product.

# Getting Started
1. Set up databases by running following script in your MySQL Workbench:

```
CREATE USER IF NOT EXISTS 'ironhacker'@'localhost' IDENTIFIED BY '1r0nh4ck3r';
GRANT ALL PRIVILEGES ON *.* TO 'ironhacker'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS env_user;
CREATE DATABASE IF NOT EXISTS env_budget_service;
CREATE DATABASE IF NOT EXISTS env_account_service;
CREATE DATABASE IF NOT EXISTS env_transactions_service;
```

2. Download or clone repository and open it in IntelliJ IDEA.
3. Run ```main()``` methods of the ```DiscoveryServiceApplication.java```, ```FeignServiceApplication.java```, ```BudgetServiceApplication.java```, ```AccountServiceApplication.java```, ```TransactionServiceApplication.java``` files.
4. Test endpoints using Postman or go to [envelope-budgeting-app-frontend](https://github.com/kat-wasik/envelope-budgeting-app-frontend) repository for instructions on how to run a frontend part of the project.

# Project Structure
Since it was a strong requirement, project is divided into 5 microservices: discovery service, feign service acting as gateway and 3 services responsible for budgets, accounts and transactions. The 3 services write into separate database and do not communicate with each other directly - all communication is done through the feign service. The feign service is also responsible for authentication and authorization of the users (using JWT web tokens).

# Endpoints

For details on endpoints go to [Swagger](http://localhost:8080/swagger-ui.html) after running the project.

## Auth
|METHOD|ROUTE|ACTION|
|---|----|----|
|POST|/api/auth/signup|Add a new user account|
|POST|/api/auth/login|Login|
|POST|/api/auth/refresh/token|Refresh token|
|POST|/api/auth/logout|Login|

## Budget
|METHOD|ROUTE|ACTION|
|---|----|----|
|GET|/api/budget|Get budget by of (logged in) user|
|POST|/api/budget|Create new budget|

## (Bank) Account
|METHOD|ROUTE|ACTION|
|---|----|----|
|GET|/api/account/{id}|Get an account|
|GET|/api/account|Get all accounts of (logged in) user|
|POST|/api/account|Create new account|
|PUT|/api/account|Update an account|
|DELETE|/api/account/{id}|Delete an account|

## Transaction
|METHOD|ROUTE|ACTION|
|---|----|----|
|GET|/api/transaction/{id}|Get a transaction|
|GET|/api/transaction/account|Get all transactions by account|
|POST|/api/transaction|Add new transaction|
|PUT|/api/transaction|Update a transaction|
|DELETE|/api/transaction/{id}|Delete a transaction|

