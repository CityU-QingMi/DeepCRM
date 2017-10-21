    private PluginVersionResult resolveFromRepository( PluginVersionRequest request )
        throws PluginVersionResolutionException
    {
        RequestTrace trace = RequestTrace.newChild( null, request );

        DefaultPluginVersionResult result = new DefaultPluginVersionResult();

        org.eclipse.aether.metadata.Metadata metadata =
            new DefaultMetadata( request.getGroupId(), request.getArtifactId(), "maven-metadata.xml",
                                 DefaultMetadata.Nature.RELEASE_OR_SNAPSHOT );

        List<MetadataRequest> requests = new ArrayList<>();

        requests.add( new MetadataRequest( metadata, null, REPOSITORY_CONTEXT ).setTrace( trace ) );

        for ( RemoteRepository repository : request.getRepositories() )
        {
            requests.add( new MetadataRequest( metadata, repository, REPOSITORY_CONTEXT ).setTrace( trace ) );
        }

        List<MetadataResult> results = repositorySystem.resolveMetadata( request.getRepositorySession(), requests );

        Versions versions = new Versions();

        for ( MetadataResult res : results )
        {
            ArtifactRepository repository = res.getRequest().getRepository();
            if ( repository == null )
            {
                repository = request.getRepositorySession().getLocalRepository();
            }

            mergeMetadata( request.getRepositorySession(), trace, versions, res.getMetadata(), repository );
        }

        selectVersion( result, request, versions );

        return result;
    }
