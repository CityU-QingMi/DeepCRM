    public static void insertSession (String id, String contextPath, String vhost)
    throws Exception
    {
        Class.forName(DRIVER_CLASS);
        try (Connection con=DriverManager.getConnection(DEFAULT_CONNECTION_URL);)
        {
            PreparedStatement statement = con.prepareStatement("insert into "+TABLE+
                                                               " ("+ID_COL+", "+CONTEXT_COL+", virtualHost, "+LAST_NODE_COL+
                                                               ", "+ACCESS_COL+", "+LAST_ACCESS_COL+", "+CREATE_COL+", "+COOKIE_COL+
                                                               ", "+LAST_SAVE_COL+", "+EXPIRY_COL+" "+") "+
                                                               " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
           
            statement.setString(1, id);
            statement.setString(2, contextPath);
            statement.setString(3,  vhost);
            statement.setString(4, "0");
            
            statement.setLong(5, System.currentTimeMillis());
            statement.setLong(6, System.currentTimeMillis());
            statement.setLong(7, System.currentTimeMillis());
            statement.setLong(8, System.currentTimeMillis());
            
            statement.setLong(9, System.currentTimeMillis());
            statement.setLong(10, System.currentTimeMillis());

            statement.execute();
            assertEquals(1,statement.getUpdateCount());
        }
    }
