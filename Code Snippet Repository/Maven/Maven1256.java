    public TestRepositoryConnector( RemoteRepository repository )
    {
        this.repository = repository;
        try
        {
            basedir = FileUtils.toFile( new URL( repository.getUrl() ) );
        }
        catch ( MalformedURLException e )
        {
            throw new IllegalStateException( e );
        }
    }
