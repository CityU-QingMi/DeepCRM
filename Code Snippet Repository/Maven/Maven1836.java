    public void testResolveParentThrowsUnresolvableModelExceptionWhenNotFound() throws Exception
    {
        final Parent parent = new Parent();
        parent.setGroupId( "ut.simple" );
        parent.setArtifactId( "artifact" );
        parent.setVersion( "0" );

        try
        {
            this.newModelResolver().resolveModel( parent );
            fail( "Expected 'UnresolvableModelException' not thrown." );
        }
        catch ( final UnresolvableModelException e )
        {
            assertNotNull( e.getMessage() );
            assertTrue( e.getMessage().startsWith( "Could not find artifact ut.simple:artifact:pom:0 in repo" ) );
        }
    }
