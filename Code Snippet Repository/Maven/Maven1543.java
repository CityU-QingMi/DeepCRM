    public void testInterpolateStringArray()
        throws Exception
    {
        Model model = new Model();

        Properties p = new Properties();
        p.setProperty( "key", "value" );
        p.setProperty( "key2", "value2" );

        String[] values = { "${key}", "${key2}" };

        StringSearchModelInterpolator interpolator = (StringSearchModelInterpolator) createInterpolator();

        ModelBuildingRequest config = createModelBuildingRequest(p);

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        interpolator.interpolateObject( values, model, new File( "." ), config, collector );
        assertProblemFree( collector );

        assertEquals( "value", values[0] );
        assertEquals( "value2", values[1] );
    }
