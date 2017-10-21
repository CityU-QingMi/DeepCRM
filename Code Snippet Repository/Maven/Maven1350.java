    @Test
    public void testMVNConfigurationThreadCanBeOverwrittenViaCommandLine()
        throws Exception
    {
        System.setProperty( MavenCli.MULTIMODULE_PROJECT_DIRECTORY,
                            new File( "src/test/projects/mavenConfigProperties" ).getCanonicalPath() );
        CliRequest request = new CliRequest( new String[]{ "-T", "5" }, null );

        cli.initialize( request );
        // read .mvn/maven.config
        cli.cli( request );

        assertEquals( "5", request.commandLine.getOptionValue( CLIManager.THREADS ) );
    }
