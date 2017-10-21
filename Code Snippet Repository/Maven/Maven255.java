    private int resolveLatestSnapshotBuildNumber( Artifact artifact, ArtifactRepository localRepository,
                                                  ArtifactRepository remoteRepository )
        throws RepositoryMetadataResolutionException
    {
        RepositoryMetadata metadata = new SnapshotArtifactRepositoryMetadata( artifact );

        getLogger().info( "Retrieving previous build number from " + remoteRepository.getId() );
        repositoryMetadataManager.resolveAlways( metadata, localRepository, remoteRepository );

        int buildNumber = 0;
        Metadata repoMetadata = metadata.getMetadata();
        if ( ( repoMetadata != null )
            && ( repoMetadata.getVersioning() != null && repoMetadata.getVersioning().getSnapshot() != null ) )
        {
            buildNumber = repoMetadata.getVersioning().getSnapshot().getBuildNumber();
        }
        return buildNumber;
    }
