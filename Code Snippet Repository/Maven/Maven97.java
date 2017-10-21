    public Artifact find( Artifact artifact )
    {
        File artifactFile = new File( getBasedir(), pathOf( artifact ) );

        // We need to set the file here or the resolver will fail with an NPE, not fully equipped to deal
        // with multiple local repository implementations yet.
        artifact.setFile( artifactFile );

        if ( artifactFile.exists() )
        {
            artifact.setResolved( true );
        }

        return artifact;
    }
