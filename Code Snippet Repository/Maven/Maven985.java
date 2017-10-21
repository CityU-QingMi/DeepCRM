    @Override
    public Artifact find( Artifact artifact )
    {
        if ( !artifact.isRelease() && buildReactor != null )
        {
            artifact = buildReactor.find( artifact );
        }

        if ( !artifact.isResolved() && ideWorkspace != null )
        {
            artifact = ideWorkspace.find( artifact );
        }

        if ( !artifact.isResolved() )
        {
            artifact = userLocalArtifactRepository.find( artifact );
        }

        return artifact;
    }
