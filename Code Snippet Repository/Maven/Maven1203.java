    public void testValidationErrorUponNonUniquePluginRepositoryIdInProfile()
        throws Exception
    {
        try
        {
            buildPom( "unique-repo-id/plugin-repo-in-profile" );
            fail( "Non-unique repository ids did not cause validation error" );
        }
        catch ( ProjectBuildingException e )
        {
            // expected
        }
    }
