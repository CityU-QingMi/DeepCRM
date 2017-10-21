    public static void createDatabase() throws SQLException {

        File derbyDirectory = new File(DERBY_DIRECTORY);
        delete(derbyDirectory);

    	Connection conn = DriverManager.getConnection(DATABASE_CONNECTION_PREFIX ,PROPS);
        try {
            Statement statement = conn.createStatement();
            for (String command : DATABASE_SETUP_STATEMENTS) {
                statement.addBatch(command);
            }
            statement.executeBatch();
        }
        finally {
            conn.close();
        }
    }
