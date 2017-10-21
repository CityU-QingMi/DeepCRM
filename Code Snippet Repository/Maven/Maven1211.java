    public void testProjectArtifactIdIsNotInheritedButMandatory()
        throws Exception
    {
        try
        {
            buildPom( "artifact-id-inheritance/child" );
            fail( "Missing artifactId did not cause validation error" );
        }
        catch ( ProjectBuildingException e )
        {
            // expected
        }
    }
