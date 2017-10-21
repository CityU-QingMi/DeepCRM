    static void createTestTables(Statement sStatement) {

        String[] demo = {
            "DROP TABLE Item IF EXISTS;", "DROP TABLE Invoice IF EXISTS;",
            "DROP TABLE Product IF EXISTS;", "DROP TABLE Customer IF EXISTS;",
            "CREATE TABLE Customer(ID INTEGER PRIMARY KEY,FirstName VARCHAR(20),"
            + "LastName VARCHAR(20),Street VARCHAR(20),City VARCHAR(20));",
            "CREATE TABLE Product(ID INTEGER PRIMARY KEY,Name VARCHAR(20),"
            + "Price DECIMAL(10,2));",
            "CREATE TABLE Invoice(ID INTEGER PRIMARY KEY,CustomerID INTEGER,"
            + "Total DECIMAL(10,2), FOREIGN KEY (CustomerId) "
            + "REFERENCES Customer(ID) ON DELETE CASCADE);",
            "CREATE TABLE Item(InvoiceID INTEGER,Item INTEGER,"
            + "ProductID INTEGER,Quantity INTEGER,Cost DECIMAL(10,2),"
            + "PRIMARY KEY(InvoiceID,Item), "
            + "FOREIGN KEY (InvoiceId) REFERENCES "
            + "Invoice (ID) ON DELETE CASCADE, FOREIGN KEY (ProductId) "
            + "REFERENCES Product(ID) ON DELETE CASCADE);"
        };

        for (int i = 0; i < demo.length; i++) {

            // drop table may fail
            try {
                sStatement.execute(demo[i]);
            } catch (SQLException e) {
                ;
            }
        }
    }
