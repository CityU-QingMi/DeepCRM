    public ArtifactRepository createArtifactRepository( String id, String url, String layoutId,
                                                        ArtifactRepositoryPolicy snapshots,
                                                        ArtifactRepositoryPolicy releases )
        throws UnknownRepositoryLayoutException
    {
        ArtifactRepositoryLayout layout = repositoryLayouts.get( layoutId );

        checkLayout( id, layoutId, layout );

        return createArtifactRepository( id, url, layout, snapshots, releases );
    }
