    @Override
    public MavenExecutionRequest addPluginGroups( List<String> pluginGroups )
    {
        for ( String pluginGroup : pluginGroups )
        {
            addPluginGroup( pluginGroup );
        }

        return this;
    }
