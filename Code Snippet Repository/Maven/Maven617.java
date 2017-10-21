    @Override
    public MavenExecutionRequest addPluginGroup( String pluginGroup )
    {
        if ( !getPluginGroups().contains( pluginGroup ) )
        {
            getPluginGroups().add( pluginGroup );
        }

        return this;
    }
