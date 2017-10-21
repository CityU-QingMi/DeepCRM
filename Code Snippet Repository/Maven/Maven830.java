    public PluginDescriptor getPluginDescriptorForPrefix( String prefix )
    {
        MavenSession session = legacySupport.getSession();

        PluginPrefixRequest request = new DefaultPluginPrefixRequest( prefix, session );

        try
        {
            PluginPrefixResult result = pluginPrefixResolver.resolve( request );

            Plugin plugin = new Plugin();
            plugin.setGroupId( result.getGroupId() );
            plugin.setArtifactId( result.getArtifactId() );

            return loadPluginDescriptor( plugin, session.getCurrentProject(), session );
        }
        catch ( Exception e )
        {
            return null;
        }
    }
