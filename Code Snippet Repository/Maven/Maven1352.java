    @Test
    public void testMVNConfigurationCLIRepeatedPropertiesLastWins()
        throws Exception
    {
        System.setProperty( MavenCli.MULTIMODULE_PROJECT_DIRECTORY,
                            new File( "src/test/projects/mavenConfigProperties" ).getCanonicalPath() );
        CliRequest request = new CliRequest( new String[]{ "-Drevision=8.1.0", "-Drevision=8.2.0" }, null );

        cli.initialize( request );
        // read .mvn/maven.config
        cli.cli( request );
        cli.properties( request );

        String revision = System.getProperty( "revision" );
        assertEquals( "8.2.0", revision );
    }
