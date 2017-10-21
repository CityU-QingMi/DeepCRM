    @Test
    public void testMavenConfig()
        throws Exception
    {
        System.setProperty( MavenCli.MULTIMODULE_PROJECT_DIRECTORY,
                            new File( "src/test/projects/config" ).getCanonicalPath() );
        CliRequest request = new CliRequest( new String[0], null );

        // read .mvn/maven.config
        cli.initialize( request );
        cli.cli( request );
        assertEquals( "multithreaded", request.commandLine.getOptionValue( CLIManager.BUILDER ) );
        assertEquals( "8", request.commandLine.getOptionValue( CLIManager.THREADS ) );

        // override from command line
        request = new CliRequest( new String[]{ "--builder", "foobar" }, null );
        cli.cli( request );
        assertEquals( "foobar", request.commandLine.getOptionValue( "builder" ) );
    }
