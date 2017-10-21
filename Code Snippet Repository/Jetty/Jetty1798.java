    public Connection getConnection ()
    throws Exception
    {
        if (!((dbDriver != null)
                &&
                (dbUrl != null)))
            throw new IllegalStateException ("Database connection information not configured");

        if(LOG.isDebugEnabled())LOG.debug("Connecting using dbDriver="+dbDriver+"+ dbUserName="+dbUserName+", dbPassword="+dbUrl);

        return DriverManager.getConnection (dbUrl,
                dbUserName,
                dbPassword);
    }
