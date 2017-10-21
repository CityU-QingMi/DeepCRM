    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String,?> sharedState,
                           Map<String,?> options)
    {
        try
        {
            super.initialize(subject, callbackHandler, sharedState, options);

            //get the jdbc  username/password, jdbc url out of the options
            dbDriver = (String)options.get("dbDriver");
            dbUrl = (String)options.get("dbUrl");
            dbUserName = (String)options.get("dbUserName");
            dbPassword = (String)options.get("dbPassword");

            if (dbUserName == null)
                dbUserName = "";

            if (dbPassword == null)
                dbPassword = "";

            if (dbDriver != null)
                Loader.loadClass(dbDriver).newInstance();
        }
        catch (ClassNotFoundException e)
        {
            throw new IllegalStateException (e.toString());
        }
        catch (InstantiationException e)
        {
            throw new IllegalStateException (e.toString());
        }
        catch (IllegalAccessException e)
        {
            throw new IllegalStateException (e.toString());
        }
    }
