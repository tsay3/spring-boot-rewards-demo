This repository contains a Spring Boot project for a simple Web API to calculate reward points for customers.

Once run on a local device, go to http://localhost:8080/points/1 to see the points calculated for a sample customer. There are 4 customers in the database. Going beyond that will not display a customer. Go to http://localhost:8080/points/ to see all the customers.

In a real-world setting, there would be more robust protections on this data. Customers would have to be authorized in order to see their own points, and only admins can see other customers' points. There would be POST endpoints to add additional transactions. There would be an additional repository containing product information to connect customers with their purchase history. For the sake of simplicity, we can imagine that each transaction already includes the sum of all products purchased (perhaps as established in a future junction table).

All code was written (c) 2025 by Thomas Say.
