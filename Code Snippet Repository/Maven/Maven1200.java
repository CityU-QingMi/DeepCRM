    public void testValidationErrorUponNonUniqueArtifactRepositoryId()
        throws Exception
    {
        try
        {
            buildPom( "unique-repo-id/artifact-repo" );
            fail( "Non-unique repository ids did not cause validation error" );
        }
        catch ( ProjectBuildingException e )
        {
            // expected
        }
    }
