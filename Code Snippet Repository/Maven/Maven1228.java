    public void testResolveDependencyThrowsUnresolvableModelExceptionWhenNotFound() throws Exception
    {
        final Dependency dependency = new Dependency();
        dependency.setGroupId( "org.apache" );
        dependency.setArtifactId( "apache" );
        dependency.setVersion( "0" );

        try
        {
            this.newModelResolver().resolveModel( dependency );
            fail( "Expected 'UnresolvableModelException' not thrown." );
        }
        catch ( final UnresolvableModelException e )
        {
            assertNotNull( e.getMessage() );
            assertTrue( e.getMessage().startsWith( "Could not find artifact org.apache:apache:pom:0 in central" ) );
        }
    }
