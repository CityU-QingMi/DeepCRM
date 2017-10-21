    public DefaultPluginPrefixRequest setPluginGroups( List<String> pluginGroups )
    {
        if ( pluginGroups != null )
        {
            this.pluginGroups = pluginGroups;
        }
        else
        {
            this.pluginGroups = Collections.emptyList();
        }

        return this;
    }
