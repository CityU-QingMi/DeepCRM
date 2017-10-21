    public void transformForInstall( Artifact artifact, ArtifactRepository localRepository )
    {
        if ( artifact.isSnapshot() )
        {
            Snapshot snapshot = new Snapshot();
            snapshot.setLocalCopy( true );
            RepositoryMetadata metadata = new SnapshotArtifactRepositoryMetadata( artifact, snapshot );

            artifact.addMetadata( metadata );
        }
    }
