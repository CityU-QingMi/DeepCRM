    public void testBaseUri()
        throws Exception
    {
        Model model = new Model();
        model.setVersion( "3.8.1" );
        model.setArtifactId( "foo" );

        Repository repository = new Repository();

        repository.setUrl( "${project.baseUri}/temp-repo" );

        model.addRepository( repository );

        ModelInterpolator interpolator = createInterpolator();

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        Model out = interpolator.interpolateModel( model, null, createModelBuildingRequest( context ), collector );
        assertProblemFree( collector );

        assertEquals( "myBaseUri/temp-repo", ( out.getRepositories().get( 0 ) ).getUrl() );
    }
