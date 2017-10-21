    @Test
    public void testMavenConfigInvalid()
        throws Exception
    {
        System.setProperty( MavenCli.MULTIMODULE_PROJECT_DIRECTORY,
                            new File( "src/test/projects/config-illegal" ).getCanonicalPath() );
        CliRequest request = new CliRequest( new String[0], null );

        cli.initialize( request );
        try
        {
            cli.cli( request );
            fail();
        }
        catch ( ParseException expected )
        {

        }
    }
