    public static InitialContext getInitialContext(final JobDataMap dataMap)
            throws NamingException {
        final Hashtable<String, String> params = new Hashtable<String, String>(4);

        final String initialContextFactory = dataMap
                .getString(INITIAL_CONTEXT_FACTORY);

        if (initialContextFactory != null)
            params.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);

        final String providerUrl = dataMap.getString(PROVIDER_URL);

        if (providerUrl != null)
            params.put(Context.PROVIDER_URL, providerUrl);

        final String principal = dataMap.getString(PRINCIPAL);

        if (principal != null)
            params.put(Context.SECURITY_PRINCIPAL, principal);

        final String credentials = dataMap.getString(CREDENTIALS);

        if (credentials != null)
            params.put(Context.SECURITY_CREDENTIALS, credentials);

        if (params.size() == 0)
            return new InitialContext();
        else
            return new InitialContext(params);

    }
