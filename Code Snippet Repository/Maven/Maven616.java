    @Override
    public MavenExecutionRequest setPluginGroups( List<String> pluginGroups )
    {
        if ( pluginGroups != null )
        {
            this.pluginGroups = new ArrayList<>( pluginGroups );
        }
        else
        {
            this.pluginGroups = null;
        }

        return this;
    }
