    public static void destroyDatabase() throws SQLException {
    	Connection conn = DriverManager.getConnection(DATABASE_CONNECTION_PREFIX ,PROPS);
        try {
            Statement statement = conn.createStatement();
            for (String command : DATABASE_TEARDOWN_STATEMENTS) {
                statement.addBatch(command);
            }
            statement.executeBatch();
        }
        finally {
            conn.close();
        }

        File derbyDirectory = new File(DERBY_DIRECTORY);
        delete(derbyDirectory);
    }
