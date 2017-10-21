    public void initService( ServiceLocator locator )
    {
        setRemoteRepositoryManager( locator.getService( RemoteRepositoryManager.class ) );
        setVersionResolver( locator.getService( VersionResolver.class ) );
        setVersionRangeResolver( locator.getService( VersionRangeResolver.class ) );
        setArtifactResolver( locator.getService( ArtifactResolver.class ) );
        modelBuilder = locator.getService( ModelBuilder.class );
        if ( modelBuilder == null )
        {
            setModelBuilder( new DefaultModelBuilderFactory().newInstance() );
        }
        setRepositoryEventDispatcher( locator.getService( RepositoryEventDispatcher.class ) );
        setLoggerFactory( locator.getService( LoggerFactory.class ) );
    }
