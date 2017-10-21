    public void testResolveParentThrowsUnresolvableModelExceptionWhenNoMatchingVersionFound() throws Exception
    {
        final Parent parent = new Parent();
        parent.setGroupId( "ut.simple" );
        parent.setArtifactId( "artifact" );
        parent.setVersion( "[2.0,2.1)" );

        try
        {
            this.newModelResolver().resolveModel( parent );
            fail( "Expected 'UnresolvableModelException' not thrown." );
        }
        catch ( final UnresolvableModelException e )
        {
            assertEquals( "No versions matched the requested parent version range '[2.0,2.1)'",
                          e.getMessage() );

        }
    }
