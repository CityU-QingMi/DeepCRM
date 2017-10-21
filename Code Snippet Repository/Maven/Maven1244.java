    @Override
    public ResolutionGroup retrieve( Artifact artifact, ArtifactRepository localRepository,
                                     List<ArtifactRepository> remoteRepositories )
        throws ArtifactMetadataRetrievalException
    {
        ResolutionGroup rg = super.retrieve( artifact, localRepository, remoteRepositories );

        for ( Artifact a : rg.getArtifacts() )
        {
            a.setResolved( true );
        }

        return rg;
    }
