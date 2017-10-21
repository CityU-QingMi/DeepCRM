    public static void main(String[] arg) {

        // Exceptions may occur
        try {

            // Load the HSQL Database Engine JDBC driver
            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            // Connect to the database
            // It will be create automatically if it does not yet exist
            // 'testfiles' in the URL is the name of the database
            // "SA" is the user name and "" is the (empty) password
            Connection conn =
                DriverManager.getConnection("jdbc:hsqldb:testfiles", "SA",
                                            "");

            // Check the command line parameters
            if (arg.length == 1) {

                // One parameter:
                // Find and print the list of files that are like this
                listFiles(conn, arg[0]);
            } else if ((arg.length == 2) && arg[0].equals("-init")) {

                // Command line parameters: -init pathname
                // Init the database and fill all file names in
                fillFileNames(conn, arg[1]);
            } else {

                // Display the usage info
                System.out.println("Usage:");
                System.out.println("java FindFile -init .");
                System.out.println("  Re-create database from directory '.'");
                System.out.println("java FindFile name");
                System.out.println("  Find files like 'name'");
            }

            // Finally, close the connection
            conn.close();
        } catch (Exception e) {

            // Print out the error message
            System.out.println(e);
            e.printStackTrace();
        }
    }
