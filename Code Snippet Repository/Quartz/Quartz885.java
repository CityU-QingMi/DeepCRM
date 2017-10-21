    protected InitialContext getInitialContext(JobDataMap jobDataMap)
        throws NamingException {
        Hashtable<String, String> params = new Hashtable<String, String>(2);
        
        String initialContextFactory =
            jobDataMap.getString(INITIAL_CONTEXT_FACTORY);
        if (initialContextFactory != null) {
            params.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
        }
        
        String providerUrl = jobDataMap.getString(PROVIDER_URL);
        if (providerUrl != null) {
            params.put(Context.PROVIDER_URL, providerUrl);
        }

        String principal = jobDataMap.getString(PRINCIPAL);
        if ( principal != null ) {
            params.put( Context.SECURITY_PRINCIPAL, principal );
        }

        String credentials = jobDataMap.getString(CREDENTIALS);
        if ( credentials != null ) {
            params.put( Context.SECURITY_CREDENTIALS, credentials );
        }

        return (params.size() == 0) ? new InitialContext() : new InitialContext(params);
    }    
