    public void testShouldInterpolateDependencyVersionToSetSameAsProjectVersion()
        throws Exception
    {
        Model model = new Model();
        model.setVersion( "3.8.1" );

        Dependency dep = new Dependency();
        dep.setVersion( "${version}" );

        model.addDependency( dep );

        ModelInterpolator interpolator = createInterpolator();

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        Model out =
            interpolator.interpolateModel( model, new File( "." ), createModelBuildingRequest( context ), collector );
        assertCollectorState(0, 0, 1, collector );

        assertEquals( "3.8.1", ( out.getDependencies().get( 0 ) ).getVersion() );
    }
