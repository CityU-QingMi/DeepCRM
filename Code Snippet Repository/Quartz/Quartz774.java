    public static void shutdownDatabase() throws SQLException {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true").close();
        } catch (SQLException e) {
            if (!("Derby system shutdown.").equals(e.getMessage())) {
                throw e;
            }
        }
        try {
            Class.forName(DATABASE_DRIVER_CLASS).newInstance();
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        } catch (InstantiationException e) {
            throw new AssertionError(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }
