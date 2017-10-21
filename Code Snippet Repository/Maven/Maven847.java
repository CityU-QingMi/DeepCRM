    private PluginPrefixResult resolveFromRepository( PluginPrefixRequest request )
    {
        RequestTrace trace = RequestTrace.newChild( null, request );

        List<MetadataRequest> requests = new ArrayList<>();

        for ( String pluginGroup : request.getPluginGroups() )
        {
            org.eclipse.aether.metadata.Metadata metadata =
                new DefaultMetadata( pluginGroup, "maven-metadata.xml", DefaultMetadata.Nature.RELEASE_OR_SNAPSHOT );

            requests.add( new MetadataRequest( metadata, null, REPOSITORY_CONTEXT ).setTrace( trace ) );

            for ( RemoteRepository repository : request.getRepositories() )
            {
                requests.add( new MetadataRequest( metadata, repository, REPOSITORY_CONTEXT ).setTrace( trace ) );
            }
        }

        // initial try, use locally cached metadata

        List<MetadataResult> results = repositorySystem.resolveMetadata( request.getRepositorySession(), requests );
        requests.clear();

        PluginPrefixResult result = processResults( request, trace, results, requests );

        if ( result != null )
        {
            return result;
        }

        // second try, refetch all (possibly outdated) metadata that wasn't updated in the first attempt

        if ( !request.getRepositorySession().isOffline() && !requests.isEmpty() )
        {
            DefaultRepositorySystemSession session =
                new DefaultRepositorySystemSession( request.getRepositorySession() );
            session.setUpdatePolicy( RepositoryPolicy.UPDATE_POLICY_ALWAYS );

            results = repositorySystem.resolveMetadata( session, requests );

            return processResults( request, trace, results, null );
        }

        return null;
    }
