    @Test
    public void testMVNConfigurationFunkyArguments()
        throws Exception
    {
        System.setProperty( MavenCli.MULTIMODULE_PROJECT_DIRECTORY,
                            new File( "src/test/projects/mavenConfigProperties" ).getCanonicalPath() );
        CliRequest request = new CliRequest(
            new String[]{ "-Drevision=8.1.0", "--file=-Dpom.xml", "\"-Dfoo=bar ", "\"-Dfoo2=bar two\"",
                "-Drevision=8.2.0" }, null );

        cli.initialize( request );
        // read .mvn/maven.config
        cli.cli( request );
        cli.properties( request );

        String revision = System.getProperty( "revision" );
        assertEquals( "8.2.0", revision );

        assertEquals( "bar ", request.getSystemProperties().getProperty( "foo" ) );
        assertEquals( "bar two", request.getSystemProperties().getProperty( "foo2" ) );

        assertEquals( "-Dpom.xml", request.getCommandLine().getOptionValue( CLIManager.ALTERNATE_POM_FILE ) );
    }
