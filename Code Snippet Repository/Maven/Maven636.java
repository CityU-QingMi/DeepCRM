    public Map<String, Object> getPluginContext( PluginDescriptor plugin, MavenProject project )
    {
        String projectKey = project.getId();

        Map<String, Map<String, Object>> pluginContextsByKey = pluginContextsByProjectAndPluginKey.get( projectKey );

        if ( pluginContextsByKey == null )
        {
            pluginContextsByKey = new ConcurrentHashMap<>();

            pluginContextsByProjectAndPluginKey.put( projectKey, pluginContextsByKey );
        }

        String pluginKey = plugin.getPluginLookupKey();

        Map<String, Object> pluginContext = pluginContextsByKey.get( pluginKey );

        if ( pluginContext == null )
        {
            pluginContext = new ConcurrentHashMap<>();

            pluginContextsByKey.put( pluginKey, pluginContext );
        }

        return pluginContext;
    }
