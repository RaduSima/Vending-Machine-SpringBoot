# Vending-Machine-SpringBoot

- In this file, I will give application testing and running instructions.

- The vending machine stores its products and money in 2 tables 
  inside a MySQL database. For application setup, the 3 SQL scripts present in the "resources" directory need to be run, in order to create the user, 
  schema and tables.

- For actually using the functionalities, you will need to send the 
  following types of request to the following URLs:
    - show all items: GET request at http://localhost:8080/items
    - show a specific item: GET request at http://localhost:8080/items/{item_id}
    - show money: GET request at http://localhost:8080/money
    - select a product: DELETE request at http://localhost:8080/items (the 
      request body needs to contain the item id of the product to be selected, as a JSON)
    - pay for a product: PUT request at http://localhost:8080/money (the
      request body needs to contain the amount of money to be paid, as a JSON)

    