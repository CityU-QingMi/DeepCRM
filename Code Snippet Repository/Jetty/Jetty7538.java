    public static boolean existsInSessionTable(String id, boolean verbose)
    throws Exception
    {
        Class.forName(DRIVER_CLASS);
        Connection con = null;
        try
        {
            con = DriverManager.getConnection(DEFAULT_CONNECTION_URL);
            PreparedStatement statement = con.prepareStatement("select * from "+
                    TABLE+
                    " where "+ID_COL+" = ?");
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            if (verbose)
            {
                boolean results = false;
                while (result.next())
                {
                    results = true;
                }
                return results;
            }
            else
                return result.next();
        }
        finally
        {
            if (con != null)
                con.close();
        }
    }
