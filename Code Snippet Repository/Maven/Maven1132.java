    @Override
    public void setUp()
        throws Exception
    {
        super.setUp();

        projectBuilder = lookup( ProjectBuilder.class );

        localRepoDir = new File( System.getProperty( "java.io.tmpdir" ), "local-repo." + System.currentTimeMillis() );
        localRepoDir.mkdirs();

        filesToDelete.add( localRepoDir );
    }
