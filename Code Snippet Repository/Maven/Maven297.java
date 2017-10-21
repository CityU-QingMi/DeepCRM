    protected void deleteArtifact( Artifact artifact, ArtifactRepository repository )
        throws Exception
    {
        String path = repository.pathOf( artifact );

        File artifactFile = new File( repository.getBasedir(), path );

        if ( artifactFile.exists() )
        {
            if ( !artifactFile.delete() )
            {
                throw new IOException( "Failure while attempting to delete artifact " + artifactFile );
            }
        }
    }
