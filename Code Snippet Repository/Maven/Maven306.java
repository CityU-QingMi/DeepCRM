    protected void createArtifact( Artifact artifact, ArtifactRepository repository )
        throws Exception
    {
        String path = repository.pathOf( artifact );

        File artifactFile = new File( repository.getBasedir(), path );

        if ( !artifactFile.getParentFile().exists() )
        {
            artifactFile.getParentFile().mkdirs();
        }
        try ( Writer writer = new OutputStreamWriter( new FileOutputStream( artifactFile ), "ISO-8859-1" ) )
        {
            writer.write( artifact.getId() );
        }
    }
