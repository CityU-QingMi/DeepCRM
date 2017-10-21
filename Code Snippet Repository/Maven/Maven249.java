    public void transformForResolve( Artifact artifact, RepositoryRequest request )
        throws ArtifactResolutionException, ArtifactNotFoundException
    {
        if ( Artifact.RELEASE_VERSION.equals( artifact.getVersion() ) )
        {
            try
            {
                String version = resolveVersion( artifact, request );

                if ( Artifact.RELEASE_VERSION.equals( version ) )
                {
                    throw new ArtifactNotFoundException( "Unable to determine the release version", artifact );
                }

                artifact.setBaseVersion( version );
                artifact.updateVersion( version, request.getLocalRepository() );
            }
            catch ( RepositoryMetadataResolutionException e )
            {
                throw new ArtifactResolutionException( e.getMessage(), artifact, e );
            }
        }
    }
