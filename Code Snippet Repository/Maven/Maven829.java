    public Plugin getPluginDefinitionForPrefix( String prefix, MavenSession session, MavenProject project )
    {
        PluginPrefixRequest request = new DefaultPluginPrefixRequest( prefix, session );
        request.setPom( project.getModel() );

        try
        {
            PluginPrefixResult result = pluginPrefixResolver.resolve( request );

            Plugin plugin = new Plugin();
            plugin.setGroupId( result.getGroupId() );
            plugin.setArtifactId( result.getArtifactId() );

            return plugin;
        }
        catch ( NoPluginFoundForPrefixException e )
        {
            return null;
        }
    }
