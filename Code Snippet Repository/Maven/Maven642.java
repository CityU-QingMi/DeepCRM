    public Map getPluginContext( PluginDescriptor plugin, MavenProject project )
    {
        Map<String, Map> pluginContextsByKey = pluginContextsByProjectAndPluginKey.get( project.getId() );

        if ( pluginContextsByKey == null )
        {
            pluginContextsByKey = new HashMap<>();
            pluginContextsByProjectAndPluginKey.put( project.getId(), pluginContextsByKey );
        }

        Map pluginContext = pluginContextsByKey.get( plugin.getPluginLookupKey() );

        if ( pluginContext == null )
        {
            pluginContext = new HashMap<>();
            pluginContextsByKey.put( plugin.getPluginLookupKey(), pluginContext );
        }

        return pluginContext;
    }
