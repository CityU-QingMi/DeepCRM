    static void fillFileNames(Connection conn,
                              String root) throws SQLException {

        System.out.println("Re-creating the database...");

        // Create a statement object
        Statement stat = conn.createStatement();

        // Try to drop the table
        try {
            stat.executeUpdate("DROP TABLE Files");
        } catch (SQLException e) {    // Ignore Exception, because the table may not yet exist
        }

        // For compatibility to other database, use varchar(255)
        // In HSQL Database Engine, length is unlimited, like Java Strings
        stat.execute("CREATE TABLE Files"
                     + "(Path varchar(255),Name varchar(255))");

        // Close the Statement object, it is no longer used
        stat.close();

        // Use a PreparedStatement because Path and Name could contain '
        PreparedStatement prep =
            conn.prepareCall("INSERT INTO Files (Path,Name) VALUES (?,?)");

        // Start with the 'root' directory and recurse all subdirectories
        fillPath(root, "", prep);

        // Close the PreparedStatement
        prep.close();
        System.out.println("Finished");
    }
