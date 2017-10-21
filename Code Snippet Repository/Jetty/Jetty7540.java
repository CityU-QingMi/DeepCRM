    public static Set<String> getSessionIds ()
    throws Exception
    {
        HashSet<String> ids = new HashSet<String>();
        Class.forName(DRIVER_CLASS);
        Connection con = null;
        try
        {
            con = DriverManager.getConnection(DEFAULT_CONNECTION_URL);
            PreparedStatement statement = con.prepareStatement("select "+ID_COL+" from "+TABLE);      
            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                ids.add(result.getString(1));
            }
            return ids;
        }
        finally
        {
            if (con != null)
                con.close();
        }
    }
