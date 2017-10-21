    private static boolean jndiAvailable()
    {
        try
        {
            Loader.loadClass(AbstractWebAppProvider.class, "org.eclipse.jetty.plus.jndi.Resource");
            Loader.loadClass(AbstractWebAppProvider.class, "org.eclipse.jetty.plus.webapp.EnvConfiguration");
            LOG.debug("JNDI support detected");
            return true;
        }
        catch (ClassNotFoundException e)
        {
            LOG.debug("No JNDI support detected");
            return false;
        }
    }
