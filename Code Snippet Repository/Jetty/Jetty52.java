    @Override
    public void init()
    {
        if (Security.getProvider("Conscrypt")==null)
        {
            Security.addProvider(new OpenSSLProvider());
            if (LOG.isDebugEnabled())
                LOG.debug("Added Conscrypt provider");
        }
    }
