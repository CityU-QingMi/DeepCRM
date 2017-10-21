    public ArtifactRepository createArtifactRepository( String repositoryId, String url,
                                                        ArtifactRepositoryLayout repositoryLayout,
                                                        ArtifactRepositoryPolicy snapshots,
                                                        ArtifactRepositoryPolicy releases )
    {
        if ( repositoryLayout == null )
        {
            repositoryLayout = layouts.get( "default" );
        }

        ArtifactRepository artifactRepository =
            artifactRepositoryFactory.createArtifactRepository( repositoryId, url, repositoryLayout, snapshots,
                                                                releases );

        return artifactRepository;
    }
