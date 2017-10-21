    private PluginPrefixResult resolveFromProject( PluginPrefixRequest request, List<Plugin> plugins )
    {
        for ( Plugin plugin : plugins )
        {
            try
            {
                PluginDescriptor pluginDescriptor =
                    pluginManager.loadPlugin( plugin, request.getRepositories(), request.getRepositorySession() );

                if ( request.getPrefix().equals( pluginDescriptor.getGoalPrefix() ) )
                {
                    return new DefaultPluginPrefixResult( plugin );
                }
            }
            catch ( Exception e )
            {
                if ( logger.isDebugEnabled() )
                {
                    logger.warn( "Failed to retrieve plugin descriptor for " + plugin.getId() + ": " + e.getMessage(),
                                 e );
                }
                else
                {
                    logger.warn( "Failed to retrieve plugin descriptor for " + plugin.getId() + ": " + e.getMessage() );
                }
            }
        }

        return null;
    }
