    private PluginVersionResult resolveFromProject( PluginVersionRequest request, List<Plugin> plugins )
    {
        for ( Plugin plugin : plugins )
        {
            if ( request.getGroupId().equals( plugin.getGroupId() )
                && request.getArtifactId().equals( plugin.getArtifactId() ) )
            {
                if ( plugin.getVersion() != null )
                {
                    return new DefaultPluginVersionResult( plugin.getVersion() );
                }
                else
                {
                    return null;
                }
            }
        }
        return null;
    }
