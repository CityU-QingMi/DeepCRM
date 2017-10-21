    public Artifact resolve( Plugin plugin, List<RemoteRepository> repositories, RepositorySystemSession session )
        throws PluginResolutionException
    {
        RequestTrace trace = RequestTrace.newChild( null, plugin );

        Artifact pluginArtifact = toArtifact( plugin, session );

        try
        {
            DefaultRepositorySystemSession pluginSession = new DefaultRepositorySystemSession( session );
            pluginSession.setArtifactDescriptorPolicy( new SimpleArtifactDescriptorPolicy( true, false ) );

            ArtifactDescriptorRequest request =
                new ArtifactDescriptorRequest( pluginArtifact, repositories, REPOSITORY_CONTEXT );
            request.setTrace( trace );
            ArtifactDescriptorResult result = repoSystem.readArtifactDescriptor( pluginSession, request );

            pluginArtifact = result.getArtifact();

            String requiredMavenVersion = (String) result.getProperties().get( "prerequisites.maven" );
            if ( requiredMavenVersion != null )
            {
                Map<String, String> props = new LinkedHashMap<>( pluginArtifact.getProperties() );
                props.put( "requiredMavenVersion", requiredMavenVersion );
                pluginArtifact = pluginArtifact.setProperties( props );
            }
        }
        catch ( ArtifactDescriptorException e )
        {
            throw new PluginResolutionException( plugin, e );
        }

        try
        {
            ArtifactRequest request = new ArtifactRequest( pluginArtifact, repositories, REPOSITORY_CONTEXT );
            request.setTrace( trace );
            pluginArtifact = repoSystem.resolveArtifact( session, request ).getArtifact();
        }
        catch ( ArtifactResolutionException e )
        {
            throw new PluginResolutionException( plugin, e );
        }

        return pluginArtifact;
    }
