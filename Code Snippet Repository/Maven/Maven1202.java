    public void testValidationErrorUponNonUniqueArtifactRepositoryIdInProfile()
        throws Exception
    {
        try
        {
            buildPom( "unique-repo-id/artifact-repo-in-profile" );
            fail( "Non-unique repository ids did not cause validation error" );
        }
        catch ( ProjectBuildingException e )
        {
            // expected
        }
    }
