    static void listFiles(Connection conn, String name) throws SQLException {

        System.out.println("Files like '" + name + "'");

        // Convert to upper case, so the search is case-insensitive
        name = name.toUpperCase();

        // Create a statement object
        Statement stat = conn.createStatement();

        // Now execute the search query
        // UCASE: This is a case insensitive search
        // ESCAPE ':' is used so it can be easily searched for '\'
        ResultSet result = stat.executeQuery("SELECT Path FROM Files WHERE "
                                             + "UCASE(Path) LIKE '%" + name
                                             + "%' ESCAPE ':'");

        // Moves to the next record until no more records
        while (result.next()) {

            // Print the first column of the result
            // could use also getString("Path")
            System.out.println(result.getString(1));
        }

        // Close the ResultSet - not really necessary, but recommended
        result.close();
    }
