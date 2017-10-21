    protected String checkSorting(String collationName) {

        String stmt1 = "DROP TABLE WORDLIST IF EXISTS;";
        String stmt2 =
            "CREATE TEXT TABLE WORDLIST ( ID INTEGER, WORD VARCHAR(50) );";
        String stmt3 = "SET TABLE WORDLIST SOURCE \"" + collationName
                       + ".csv;encoding=UTF-8\"";
        String selectStmt    = "SELECT ID, WORD FROM WORDLIST ORDER BY WORD";
        String returnMessage = "";

        try {

            // set database collation
            statement.execute(getSetCollationStmt(collationName));
            statement.execute(stmt1);
            statement.execute(stmt2);
            statement.execute(stmt3);

            ResultSet results = statement.executeQuery(selectStmt);

            while (results.next()) {
                int expectedPosition = results.getInt(1);
                int foundPosition    = results.getRow();

                if (expectedPosition != foundPosition) {
                    String word = results.getString(2);

                    return "testing collation '" + collationName
                           + "' failed\n" + "  word              : " + word
                           + "\n" + "  expected position : "
                           + expectedPosition + "\n"
                           + "  found position    : " + foundPosition + "\n";
                }
            }
        } catch (SQLException e) {
            return "testing collation '" + collationName
                   + "' failed\n  exception message: " + e.getMessage() + "\n";
        }

        return "";
    }
