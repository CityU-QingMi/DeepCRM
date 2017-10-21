    protected void setUp()
        throws Exception
    {
        super.setUp();

        if ( getContainer().hasComponent( ProjectBuilder.class, "test" ) )
        {
            projectBuilder = lookup( ProjectBuilder.class, "test" );
        }
        else
        {
            // default over to the main project builder...
            projectBuilder = lookup( ProjectBuilder.class );
        }

        repositorySystem = lookup( RepositorySystem.class );
    }
