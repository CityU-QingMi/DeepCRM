    public void testPartialResultUponBadDependencyDeclaration()
        throws Exception
    {
        File pomFile = getTestFile( "src/test/resources/projects/bad-dependency.xml" );

        try
        {
            ProjectBuildingRequest request = newBuildingRequest();
            request.setProcessPlugins( false );
            request.setResolveDependencies( true );
            projectBuilder.build( pomFile, request );
            fail( "Project building did not fail despite invalid POM" );
        }
        catch ( ProjectBuildingException e )
        {
            List<ProjectBuildingResult> results = e.getResults();
            assertNotNull( results );
            assertEquals( 1, results.size() );
            ProjectBuildingResult result = results.get( 0 );
            assertNotNull( result );
            assertNotNull( result.getProject() );
            assertEquals( 1, result.getProblems().size() );
            assertEquals( 1, result.getProject().getArtifacts().size() );
            assertNotNull( result.getDependencyResolutionResult() );
        }
    }
