    public void transformForDeployment( Artifact artifact, ArtifactRepository remoteRepository,
                                        ArtifactRepository localRepository )
        throws ArtifactDeploymentException
    {
        if ( artifact.isSnapshot() )
        {
            Snapshot snapshot = new Snapshot();

            snapshot.setTimestamp( getDeploymentTimestamp() );

            // we update the build number anyway so that it doesn't get lost. It requires the timestamp to take effect
            try
            {
                int buildNumber = resolveLatestSnapshotBuildNumber( artifact, localRepository, remoteRepository );

                snapshot.setBuildNumber( buildNumber + 1 );
            }
            catch ( RepositoryMetadataResolutionException e )
            {
                throw new ArtifactDeploymentException( "Error retrieving previous build number for artifact '"
                    + artifact.getDependencyConflictId() + "': " + e.getMessage(), e );
            }

            RepositoryMetadata metadata = new SnapshotArtifactRepositoryMetadata( artifact, snapshot );

            artifact.setResolvedVersion(
                constructVersion( metadata.getMetadata().getVersioning(), artifact.getBaseVersion() ) );

            artifact.addMetadata( metadata );
        }
    }
