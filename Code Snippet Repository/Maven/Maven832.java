    public PluginDescriptor verifyPlugin( Plugin plugin, MavenProject project, Settings settings,
                                          ArtifactRepository localRepository )
        throws ArtifactResolutionException, PluginVersionResolutionException, ArtifactNotFoundException,
        InvalidVersionSpecificationException, InvalidPluginException, PluginManagerException, PluginNotFoundException,
        PluginVersionNotFoundException
    {
        MavenSession session = legacySupport.getSession();

        if ( plugin.getVersion() == null )
        {
            PluginVersionRequest versionRequest =
                new DefaultPluginVersionRequest( plugin, session.getRepositorySession(),
                                                 project.getRemotePluginRepositories() );
            plugin.setVersion( pluginVersionResolver.resolve( versionRequest ).getVersion() );
        }

        try
        {
            return pluginManager.getPluginDescriptor( plugin, project.getRemotePluginRepositories(),
                                                      session.getRepositorySession() );
        }
        catch ( PluginResolutionException e )
        {
            throw new PluginNotFoundException( plugin, project.getPluginArtifactRepositories() );
        }
        catch ( PluginDescriptorParsingException | InvalidPluginDescriptorException e )
        {
            throw new PluginManagerException( plugin, e.getMessage(), e );
        }
    }
