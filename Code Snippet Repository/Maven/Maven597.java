    @Override
    public MavenExecutionRequest setLocalRepository( ArtifactRepository localRepository )
    {
        this.localRepository = localRepository;

        if ( localRepository != null )
        {
            setLocalRepositoryPath( new File( localRepository.getBasedir() ).getAbsoluteFile() );
        }

        return this;
    }
