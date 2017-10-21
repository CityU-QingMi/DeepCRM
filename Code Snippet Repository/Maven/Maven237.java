    public ArtifactRepository createDeploymentArtifactRepository( String id, String url, String layoutId,
                                                                  boolean uniqueVersion )
        throws UnknownRepositoryLayoutException
    {
        ArtifactRepositoryLayout layout = repositoryLayouts.get( layoutId );

        checkLayout( id, layoutId, layout );

        return createDeploymentArtifactRepository( id, url, layout, uniqueVersion );
    }
