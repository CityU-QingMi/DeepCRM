    public void testShouldNotInterpolateDependencyVersionWithInvalidReference()
        throws Exception
    {
        Model model = new Model();
        model.setVersion( "3.8.1" );

        Dependency dep = new Dependency();
        dep.setVersion( "${something}" );

        model.addDependency( dep );

/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/

        ModelInterpolator interpolator = createInterpolator();

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        Model out =
            interpolator.interpolateModel( model, new File( "." ), createModelBuildingRequest( context ), collector );
        assertProblemFree( collector );

        assertEquals( "${something}", ( out.getDependencies().get( 0 ) ).getVersion() );
    }
