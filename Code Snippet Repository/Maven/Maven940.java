    public ModelSource resolveModel( String groupId, String artifactId, String version )
        throws UnresolvableModelException
    {
        File pomFile = null;

        if ( modelPool != null )
        {
            pomFile = modelPool.get( groupId, artifactId, version );
        }

        if ( pomFile == null )
        {
            Artifact pomArtifact = new DefaultArtifact( groupId, artifactId, "", "pom", version );

            try
            {
                ArtifactRequest request = new ArtifactRequest( pomArtifact, repositories, context );
                request.setTrace( trace );
                pomArtifact = resolver.resolveArtifact( session, request ).getArtifact();
            }
            catch ( ArtifactResolutionException e )
            {
                throw new UnresolvableModelException( e.getMessage(), groupId, artifactId, version, e );
            }

            pomFile = pomArtifact.getFile();
        }

        return new FileModelSource( pomFile );
    }
