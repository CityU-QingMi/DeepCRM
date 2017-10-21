    public void testFinalFieldsExcludedFromInterpolation()
    {
        Properties props = new Properties();
        props.setProperty( "expression", "value" );
        DefaultModelBuildingRequest request = new DefaultModelBuildingRequest();
        request.setUserProperties( props );

        SimpleProblemCollector problems = new SimpleProblemCollector();
        StringSearchModelInterpolator interpolator = new StringSearchModelInterpolator();
        interpolator.interpolateObject( new ClassWithFinalField(), new Model(), null, request, problems );

        assertProblemFree(  problems );
    }
