    public void testTwoReferences()
        throws Exception
    {
        Model model = new Model();
        model.setVersion( "3.8.1" );
        model.setArtifactId( "foo" );

        Dependency dep = new Dependency();
        dep.setVersion( "${artifactId}-${version}" );

        model.addDependency( dep );

        ModelInterpolator interpolator = createInterpolator();

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        Model out =
            interpolator.interpolateModel( model, new File( "." ), createModelBuildingRequest( context ), collector );
        assertCollectorState( 0, 0, 2, collector );

        assertEquals( "foo-3.8.1", ( out.getDependencies().get( 0 ) ).getVersion() );
    }
