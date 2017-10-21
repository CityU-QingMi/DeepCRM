    public void testLocationTrackerShouldBeExcludedFromInterpolation()
    {
        Properties props = new Properties();
        props.setProperty( "expression", "value" );
        DefaultModelBuildingRequest request = new DefaultModelBuildingRequest();
        request.setUserProperties( props );

        InputSource source = new InputSource();
        source.setLocation( "${expression}" );
        source.setModelId( "${expression}" );
        Model model = new Model();
        model.setLocation( "", new InputLocation( 1, 1, source ) );

        SimpleProblemCollector problems = new SimpleProblemCollector();
        StringSearchModelInterpolator interpolator = new StringSearchModelInterpolator();
        interpolator.interpolateObject( model, model, null, request, problems );

        assertProblemFree( problems );
        assertEquals( "${expression}", source.getLocation() );
        assertEquals( "${expression}", source.getModelId() );
    }
