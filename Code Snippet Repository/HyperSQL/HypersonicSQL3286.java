    protected void doCreate() {

        try {
            Connection con = getConnection("sa", "", false);

            if (con != null) {
                Statement stmt = con.createStatement();

                stmt.execute("SET PASSWORD 'password'");
                stmt.execute("CREATE USER abcd PASSWORD 'dcba'");
                stmt.execute("CREATE SEQUENCE MySeq");
                stmt.execute(
                    "CREATE TABLE MyTable (Id INT PRIMARY KEY, Name VARCHAR(100) NOT NULL)");
                stmt.execute("CREATE TABLE Dummy (Blah VARCHAR(100) NOT NULL)");
                stmt.execute(
                    "INSERT INTO Dummy (Blah) VALUES ('dummy value')");
                stmt.execute("GRANT ALL ON MyTable TO abcd");
                stmt.execute("GRANT ALL ON Dummy TO abcd");
                stmt.execute("GRANT ALL ON SEQUENCE MySeq TO abcd");
                stmt.close();
                con.close();
                textArea.setText("Database created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
