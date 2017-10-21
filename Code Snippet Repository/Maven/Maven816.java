    public synchronized PluginDescriptor getPluginDescriptor( Plugin plugin, List<RemoteRepository> repositories,
                                                              RepositorySystemSession session )
        throws PluginResolutionException, PluginDescriptorParsingException, InvalidPluginDescriptorException
    {
        PluginDescriptorCache.Key cacheKey = pluginDescriptorCache.createKey( plugin, repositories, session );

        PluginDescriptor pluginDescriptor = pluginDescriptorCache.get( cacheKey );

        if ( pluginDescriptor == null )
        {
            org.eclipse.aether.artifact.Artifact artifact =
                pluginDependenciesResolver.resolve( plugin, repositories, session );

            Artifact pluginArtifact = RepositoryUtils.toArtifact( artifact );

            pluginDescriptor = extractPluginDescriptor( pluginArtifact, plugin );

            pluginDescriptor.setRequiredMavenVersion( artifact.getProperty( "requiredMavenVersion", null ) );

            pluginDescriptorCache.put( cacheKey, pluginDescriptor );
        }

        pluginDescriptor.setPlugin( plugin );

        return pluginDescriptor;
    }
