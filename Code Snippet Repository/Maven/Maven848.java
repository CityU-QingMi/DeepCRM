    private PluginPrefixResult processResults( PluginPrefixRequest request, RequestTrace trace,
                                               List<MetadataResult> results, List<MetadataRequest> requests )
    {
        for ( MetadataResult res : results )
        {
            org.eclipse.aether.metadata.Metadata metadata = res.getMetadata();

            if ( metadata != null )
            {
                ArtifactRepository repository = res.getRequest().getRepository();
                if ( repository == null )
                {
                    repository = request.getRepositorySession().getLocalRepository();
                }

                PluginPrefixResult result =
                    resolveFromRepository( request, trace, metadata.getGroupId(), metadata, repository );

                if ( result != null )
                {
                    return result;
                }
            }

            if ( requests != null && !res.isUpdated() )
            {
                requests.add( res.getRequest() );
            }
        }

        return null;
    }
