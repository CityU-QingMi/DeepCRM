    public void testResolveParentThrowsUnresolvableModelExceptionWhenNotFound() throws Exception
    {
        final Parent parent = new Parent();
        parent.setGroupId( "org.apache" );
        parent.setArtifactId( "apache" );
        parent.setVersion( "0" );

        try
        {
            this.newModelResolver().resolveModel( parent );
            fail( "Expected 'UnresolvableModelException' not thrown." );
        }
        catch ( final UnresolvableModelException e )
        {
            assertNotNull( e.getMessage() );
            assertTrue( e.getMessage().startsWith( "Could not find artifact org.apache:apache:pom:0 in central" ) );
        }
    }
