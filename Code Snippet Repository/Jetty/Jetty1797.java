    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String,?> sharedState,
                           Map<String,?> options)
    {
        try
        {
            super.initialize(subject, callbackHandler, sharedState, options);

            //get the datasource jndi name
            dbJNDIName = (String)options.get("dbJNDIName");

            InitialContext ic = new InitialContext();
            dataSource = (DataSource)ic.lookup("java:comp/env/"+dbJNDIName);
        }
        catch (NamingException e)
        {
            throw new IllegalStateException (e.toString());
        }
    }
