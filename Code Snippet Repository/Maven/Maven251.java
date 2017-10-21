    public void transformForResolve( Artifact artifact, RepositoryRequest request )
        throws ArtifactResolutionException
    {
        // Only select snapshots that are unresolved (eg 1.0-SNAPSHOT, not 1.0-20050607.123456)
        if ( artifact.isSnapshot() && artifact.getBaseVersion().equals( artifact.getVersion() ) )
        {
            try
            {
                String version = resolveVersion( artifact, request );
                artifact.updateVersion( version, request.getLocalRepository() );
            }
            catch ( RepositoryMetadataResolutionException e )
            {
                throw new ArtifactResolutionException( e.getMessage(), artifact, e );
            }
        }
    }
