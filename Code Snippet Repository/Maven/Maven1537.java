    public void testShouldThrowExceptionOnRecursiveScmConnectionReference()
        throws Exception
    {
        Model model = new Model();

        Scm scm = new Scm();
        scm.setConnection( "${project.scm.connection}/somepath" );

        model.setScm( scm );

        try
        {
            ModelInterpolator interpolator = createInterpolator();

            final SimpleProblemCollector collector = new SimpleProblemCollector();
            interpolator.interpolateModel( model, null, createModelBuildingRequest( context ), collector );
            assertCollectorState(  0, 1, 0, collector );
        }
        catch ( Exception e )
        {

        }
    }
