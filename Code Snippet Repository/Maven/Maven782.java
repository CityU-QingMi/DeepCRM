    public ClassRealm getPluginRealm( MavenSession session, PluginDescriptor pluginDescriptor )
        throws PluginResolutionException, PluginManagerException
    {
        ClassRealm pluginRealm = pluginDescriptor.getClassRealm();
        if ( pluginRealm != null )
        {
            return pluginRealm;
        }

        mavenPluginManager.setupPluginRealm( pluginDescriptor, session, null, null, null );

        return pluginDescriptor.getClassRealm();
    }
