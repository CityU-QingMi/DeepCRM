    public void testInterpolateObjectWithStringToStringMapField()
        throws Exception
    {
        Model model = new Model();

        Properties p = new Properties();
        p.setProperty( "key", "value" );
        p.setProperty( "key2", "value2" );

        Map<String, String> values = new HashMap<>();
        values.put( "key", "${key}" );
        values.put( "key2", "${key2}" );

        ObjectWithMapField obj = new ObjectWithMapField( values );

        StringSearchModelInterpolator interpolator = (StringSearchModelInterpolator) createInterpolator();

        ModelBuildingRequest config = createModelBuildingRequest( p );

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        interpolator.interpolateObject( obj, model, new File( "." ), config, collector );
        assertProblemFree( collector );

        assertEquals( "value", obj.values.get( "key" ) );
        assertEquals( "value2", obj.values.get( "key2" ) );
    }
