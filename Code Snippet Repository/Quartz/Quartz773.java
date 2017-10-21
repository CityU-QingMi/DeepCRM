    public static void destroyDatabase(String name) throws SQLException {
        try {
            DriverManager.getConnection(
                    DATABASE_CONNECTION_PREFIX + name + ";drop=true").close();
        } catch (SQLException e) {
            if (!("Database 'memory:" + name + "' dropped.").equals(e.getMessage())) {
                throw e;
            }
        }
    }
