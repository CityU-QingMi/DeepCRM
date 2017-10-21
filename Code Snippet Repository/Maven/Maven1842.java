    public void testResolveDependencyThrowsUnresolvableModelExceptionWhenNotFound() throws Exception
    {
        final Dependency dependency = new Dependency();
        dependency.setGroupId( "ut.simple" );
        dependency.setArtifactId( "artifact" );
        dependency.setVersion( "0" );

        try
        {
            this.newModelResolver().resolveModel( dependency );
            fail( "Expected 'UnresolvableModelException' not thrown." );
        }
        catch ( final UnresolvableModelException e )
        {
            assertNotNull( e.getMessage() );
            assertTrue( e.getMessage().startsWith( "Could not find artifact ut.simple:artifact:pom:0 in repo" ) );
        }
    }
