    public void testInterpolateObjectWithStringToStringArrayMapField()
        throws Exception
    {
        Model model = new Model();

        Properties p = new Properties();
        p.setProperty( "key", "value" );
        p.setProperty( "key2", "value2" );
        p.setProperty( "key3", "value3" );
        p.setProperty( "key4", "value4" );

        Map<String, String[]> values = new HashMap<>();
        values.put( "key", new String[] { "${key}", "${key2}" } );
        values.put( "key2", new String[] { "${key3}", "${key4}" } );

        ObjectWithMapField obj = new ObjectWithMapField( values );

        StringSearchModelInterpolator interpolator = (StringSearchModelInterpolator) createInterpolator();

        ModelBuildingRequest config = createModelBuildingRequest( p );

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        interpolator.interpolateObject( obj, model, new File( "." ), config, collector );
        assertProblemFree( collector );

        assertEquals( "value", ( (String[]) obj.values.get( "key" ) )[0] );
        assertEquals( "value2", ( (String[]) obj.values.get( "key" ) )[1] );
        assertEquals( "value3", ( (String[]) obj.values.get( "key2" ) )[0] );
        assertEquals( "value4", ( (String[]) obj.values.get( "key2" ) )[1] );
    }
