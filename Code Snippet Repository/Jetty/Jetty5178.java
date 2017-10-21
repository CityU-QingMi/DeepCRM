    @Override
    public void prevent(ClassLoader loader)
    {
        try
        {
            Class.forName("com.sun.jndi.LdapPoolManager", true, loader);
        }
        catch (ClassNotFoundException e)
        {
            LOG.ignore(e);
        }
    }
