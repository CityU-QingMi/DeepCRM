    public PluginDescriptor loadPluginFully( Plugin plugin, MavenProject project, MavenSession session )
        throws ArtifactResolutionException, PluginVersionResolutionException, ArtifactNotFoundException,
        InvalidVersionSpecificationException, InvalidPluginException, PluginManagerException, PluginNotFoundException,
        PluginVersionNotFoundException
    {
        PluginDescriptor pluginDescriptor = loadPluginDescriptor( plugin, project, session );

        try
        {
            pluginManager.setupPluginRealm( pluginDescriptor, session, null, null, null );
        }
        catch ( PluginResolutionException e )
        {
            throw new PluginManagerException( plugin, e.getMessage(), e );
        }

        return pluginDescriptor;
    }
