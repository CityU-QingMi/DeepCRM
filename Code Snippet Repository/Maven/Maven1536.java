    public void testShouldNotThrowExceptionOnReferenceToNonExistentValue()
        throws Exception
    {
        Model model = new Model();

        Scm scm = new Scm();
        scm.setConnection( "${test}/somepath" );

        model.setScm( scm );

        ModelInterpolator interpolator = createInterpolator();

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        Model out =
            interpolator.interpolateModel( model, new File( "." ), createModelBuildingRequest( context ), collector );

        assertProblemFree(  collector );
        assertEquals( "${test}/somepath", out.getScm().getConnection() );
    }
