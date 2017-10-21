    private void setupDatabase() {

        try {
            m_connection = newConnection();
            m_statement  = m_connection.createStatement();

            executeStatement("DROP TABLE ABC IF EXISTS CASCADE");
            executeStatement("DROP TABLE TABLE_A IF EXISTS CASCADE");
            executeStatement("DROP TABLE TABLE_B IF EXISTS CASCADE");
            executeStatement("DROP VIEW V1 IF EXISTS CASCADE"); // V1 is created by a previous test case
            executeStatement(
                "CREATE TABLE ABC (ID INTEGER NOT NULL PRIMARY KEY, A VARCHAR(50), B VARCHAR(50), C VARCHAR(50))");
            executeStatement("INSERT INTO ABC VALUES (1, 'a', 'b', 'c')");
            executeStatement("INSERT INTO ABC VALUES (2, 'd', 'e', 'f')");
            executeStatement(
                "CREATE TABLE TABLE_A (ID_A INTEGER NOT NULL PRIMARY KEY, NAME_A VARCHAR(50))");
            executeStatement("INSERT INTO TABLE_A VALUES (1, 'first A')");
            executeStatement("INSERT INTO TABLE_A VALUES (2, 'second A')");
            executeStatement(
                "CREATE TABLE TABLE_B (ID_B INTEGER NOT NULL PRIMARY KEY, NAME_B VARCHAR(50))");
            executeStatement("INSERT INTO TABLE_B VALUES (1, 'first B')");
            executeStatement("INSERT INTO TABLE_B VALUES (2, 'second B')");
        } catch (SQLException ex) {
            fail(ex.toString());
        }
    }
