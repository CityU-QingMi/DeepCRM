    public void testShouldInterpolateUnprefixedBasedirExpression()
        throws Exception
    {
        File basedir = new File( "/test/path" );
        Model model = new Model();
        Dependency dep = new Dependency();
        dep.setSystemPath( "${basedir}/artifact.jar" );

        model.addDependency( dep );

        ModelInterpolator interpolator = createInterpolator();

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        Model result = interpolator.interpolateModel( model, basedir, createModelBuildingRequest( context ), collector );
        assertProblemFree(  collector );


        List<Dependency> rDeps = result.getDependencies();
        assertNotNull( rDeps );
        assertEquals( 1, rDeps.size() );
        assertEquals( new File( basedir, "artifact.jar" ).getAbsolutePath(),
                      new File( rDeps.get( 0 ).getSystemPath() ).getAbsolutePath() );
    }
