    public void testValidationErrorUponNonUniquePluginRepositoryId()
        throws Exception
    {
        try
        {
            buildPom( "unique-repo-id/plugin-repo" );
            fail( "Non-unique repository ids did not cause validation error" );
        }
        catch ( ProjectBuildingException e )
        {
            // expected
        }
    }
