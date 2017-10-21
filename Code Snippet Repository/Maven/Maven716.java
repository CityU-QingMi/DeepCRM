    public Plugin findPluginForPrefix( String prefix, MavenSession session )
        throws NoPluginFoundForPrefixException
    {
        // [prefix]:[goal]

        if ( session.getCurrentProject() != null )
        {
            try
            {
                lifecyclePluginResolver.resolveMissingPluginVersions( session.getCurrentProject(), session );
            }
            catch ( PluginVersionResolutionException e )
            {
                // not critical here
                logger.debug( e.getMessage(), e );
            }
        }

        PluginPrefixRequest prefixRequest = new DefaultPluginPrefixRequest( prefix, session );
        PluginPrefixResult prefixResult = pluginPrefixResolver.resolve( prefixRequest );

        Plugin plugin = new Plugin();
        plugin.setGroupId( prefixResult.getGroupId() );
        plugin.setArtifactId( prefixResult.getArtifactId() );

        return plugin;
    }
