    private boolean isCompatible( PluginVersionRequest request, String version )
    {
        Plugin plugin = new Plugin();
        plugin.setGroupId( request.getGroupId() );
        plugin.setArtifactId( request.getArtifactId() );
        plugin.setVersion( version );

        PluginDescriptor pluginDescriptor;

        try
        {
            pluginDescriptor =
                pluginManager.getPluginDescriptor( plugin, request.getRepositories(), request.getRepositorySession() );
        }
        catch ( PluginResolutionException e )
        {
            logger.debug( "Ignoring unresolvable plugin version " + version, e );
            return false;
        }
        catch ( Exception e )
        {
            // ignore for now and delay failure to higher level processing
            return true;
        }

        try
        {
            pluginManager.checkRequiredMavenVersion( pluginDescriptor );
        }
        catch ( Exception e )
        {
            logger.debug( "Ignoring incompatible plugin version " + version + ": " + e.getMessage() );
            return false;
        }

        return true;
    }
