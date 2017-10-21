    public Map<String, Object> getPluginComponents( Plugin plugin, String role )
        throws ComponentLookupException, PluginManagerException
    {
        MavenSession session = legacySupport.getSession();

        PluginDescriptor pluginDescriptor;
        try
        {
            pluginDescriptor =
                pluginManager.getPluginDescriptor( plugin, session.getCurrentProject().getRemotePluginRepositories(),
                                                   session.getRepositorySession() );

            pluginManager.setupPluginRealm( pluginDescriptor, session, null, null, null );
        }
        catch ( Exception e )
        {
            throw new PluginManagerException( plugin, e.getMessage(), e );
        }

        ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
        try
        {
            Thread.currentThread().setContextClassLoader( pluginDescriptor.getClassRealm() );

            return container.lookupMap( role );
        }
        finally
        {
            Thread.currentThread().setContextClassLoader( oldClassLoader );
        }
    }
