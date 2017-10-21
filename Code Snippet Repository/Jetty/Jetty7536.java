    public static void shutdown (String connectionUrl)
    throws Exception
    {
        if (connectionUrl == null)
            connectionUrl = DEFAULT_SHUTDOWN_URL;
        
        try
        {
            DriverManager.getConnection(connectionUrl);
        }
        catch( SQLException expected )
        {
            if (!"08006".equals(expected.getSQLState()))
            {
               throw expected;
            }
        }
    }
